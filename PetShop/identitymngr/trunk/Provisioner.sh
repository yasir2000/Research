#!/bin/ksh
# ------------------------------------------------------------------------------------------------
# provisioner.sh
#
# This script controls Provisioner's process execution
#
# This scripts receives the following parameters:
#  'start'  : launchs Provisioner.
#  'stop'   : sends SIGTERM signal to the running Provisioner process. Provisioner 
#             receives the signal and terminates gracefully and deletes Provisioner.pid
#             file.
#  'refresh': sends SIGCONT signal to the running Provisioner. It receives
#             the signal and re-load its configuration.
#
# Change log:
# F. Hevia, 11/06/2007 First version.
# G.Espert 12/12/2007 Added config dir and JAR file name.
# G.Espert 12/14/2007 Added maximum amount of consumers.
# G.Espert 04/02/2007 Added country and language parameters for localization. Thanks Martin Mierse
# ------------------------------------------------------------------------------------------------
progname="provisioner.sh"
version="0.0.1"

##################################################
################  CONFIGURATION  #################
##################################################

##### Executable directory
BIN=bin

##### Provisioner's PID file name and path
PIDFILE=provisioner.pid

##### Default value for EXEC_INTERVAL
EXEC_INTERVAL=20

##### Default value for COUNTRY
COUNTRY=US

##### Default value for LANGUAGE
LANGUAGE=en

##### Default value for MAX_CONSUMERS
MAX_CONSUMERS=4

##### Default value for CONFIG_DIR
CONFIG_DIR=conf/

##### Default executable JAR file name
JAR_NAME=provisioner.jar

##### Korn shell configuration file
CFGFILE=provisioner.cfg

# ------------------------------------------------------------------------------------------
# Function:    get_config_val
# Description: Reads CFGFILE 
# Used by:     read_config
# ------------------------------------------------------------------------------------------
get_config_val()
{
   value=`awk -v parm=$1 -F'=' 'BEGIN {parm=sprintf("^%s", parm) } ($1 ~ parm) { print $2 }' $CFGFILE`
   if [ -z $value ]
   then
      errlog "Error: parameter [$1] not found on [$CFGFILE]"
      exit 1
   fi
   echo $value
}


# ------------------------------------------------------------------------------------------
# Function:    read_config
# Description: Reads configuration
# Used by:     main
# ------------------------------------------------------------------------------------------
read_config()
{
   errlog "Reading configuration ..."
   
   if [ ! -f "$CFGFILE" ]
   then
      errlog "Fatal error: Couldn't find configuration file $CFGFILE"
      exit 1
   fi
   
   #### Interval for searching new pending provisioning requirements
   EXEC_INTERVAL=`get_config_val "EXEC_INTERVAL"`
   
   #### Specify where the configuration files are stored
   CONFIG_DIR=`get_config_val "CONFIG_DIR"`
   
   #### Specify the maximum amount of consumer threads to be created
   MAX_CONSUMERS=`get_config_val "MAX_CONSUMERS"`
   
   ##### Specify executable JAR file name
   JAR_NAME=`get_config_val "JAR_NAME"`

   ##### Specify country
   COUNTRY=`get_config_val "COUNTRY"`

   ##### Specify language
   LANGUAGE=`get_config_val "LANGUAGE"`
   
}

# ------------------------------------------------------------------------------------------
# Function:    errlog
# Description: Echo errors to the log file
# Used by:     all
# ------------------------------------------------------------------------------------------
errlog()
{
   printf "[%s %s][%06d] %s\n" `date +"%Y-%m-%d %H:%M:%S"` $$ "$1" | tee -a $LOGFILE
}

# ------------------------------------------------------------------------------------------
# Function:    f_error
# Description: Generates error e-mail 
# Used by:     todas
# ------------------------------------------------------------------------------------------
f_error()
{
mailx -s "provisioner.sh: execution error" ${MAIL_ALARMA} <<EOT
Error details not available.
`echo $1`
EOT

        return 0
}


# ------------------------------------------------------------------------------------------
# Function:    process_status
# Description: Determine wether Provisioner is running
# Used by:     all
# ------------------------------------------------------------------------------------------
process_status()
{
    if [ -e $PIDFILE ]
    then
       #echo "Provisioner is up and running"
       pid=`cat $PIDFILE`
       echo $pid
       return $pid
    fi
    #echo "Provisioner is down"
    echo 0
    return 0
}

# ------------------------------------------------------------------------------------------
# Function:    process_signal
# Description: Signal catcher
# Used by:     all
# ------------------------------------------------------------------------------------------
process_signal()
{
    # Termination signal received
    if [ $1 -eq 1 ]; then
       errlog "SIGHUP: reload configuration"
       process_RUN=2
    else       
       errlog "Termination signal received"
       process_RUN=0
    fi
    return 0
}

# ------------------------------------------------------------------------------------------
# Function:    process_exit
# Description: Ends Provisioner process
# Used by:     all
# ------------------------------------------------------------------------------------------
process_exit()
{
    rm -f $PIDFILE
#    if [ "$1" -eq 0 ]; then
#        errlog "$progname gracefully ended"
#    else
#        errlog "$progname ended with error $1"
#    fi
    exit $1
}
    
# ------------------------------------------------------------------------------------------
# Function:    report_error
# Description: Emails error
# Used by:     all
# ------------------------------------------------------------------------------------------
report_error()
{
      # Sends error email
      mailx -s "provisioner.sh" $MAIL_ALARMA <<EOT
$1

EOT
    
    return 0
}



##################################################
############ MAIN ################################
##################################################

# Launch Provisioner: read configuration
read_config

if [ $# -lt 1 -o "$1" != "start" -a "$1" != "stop" -a "$1" != "refresh" -a "$1" != "status" ]; then
   echo "provisioner.sh"
   echo "usage: $progname start | stop | refresh | status"
   exit 1
fi


##### Refresh Provisioner ################################################
if [ $1 = "refresh" ]
then
    errlog "Reloading $progname configuration..."
    provisioner_pid=`process_status`
    if [ "$provisioner_pid" -gt 0 ]; then
        errlog "Refresh signal sent to $provisioner_pid"
        kill -1 $provisioner_pid
        if [ $? -ne 0 ]; then
            errlog "Process does not exist: deleting file `basename $PIDFILE` to keep consistency"
            rm -f $PIDFILE
        fi
    else
        errlog "Provisioner is not running"
    fi
    exit 0
fi


##### Stop Provisioner ##################################################
if [ $1 = "stop" ]
then
    errlog "Stoping $progname ..."
    provisioner_pid=`process_status`
    if [ "$provisioner_pid" -gt 0 ]; then
        errlog "Termination signal sent to $provisioner_pid"
        kill $provisioner_pid
        if [ $? -ne 0 ]; then
            errlog "Process does not exist: deleting file `basename $PIDFILE` to keep consistency"
            rm -f $PIDFILE
        fi
    else
        errlog "Provisioner is not running"
    fi
    exit 0
fi


##### Get Provisioner Status ##################################################
if [ $1 = "status" ]
then
    # Check if Provisioner is already running
    provisioner_pid=`process_status`
    if [ "$provisioner_pid" -gt 0 ]; then
        errlog "Provisioner is up and running. Its PID is $provisioner_pid"
        ps -Fp $provisioner_pid
        exit 0
    fi
    errlog "Provisioner is not running"
    exit 1 
fi


##### Start Provisioner ##################################################
if [ $1 = "start" ]
then
    # Check if Provisioner is already running
    provisioner_pid=`process_status`
    if [ "$provisioner_pid" -gt 0 ]; then
        errlog "Provisioner is up and running. Its PID is $provisioner_pid"
        ps -Fp $provisioner_pid
        exit 0
    fi

    errlog "Starting $progname v${version}"
    
    nohup java -jar $BIN/$JAR_NAME $EXEC_INTERVAL $MAX_CONSUMERS $CONFIG_DIR $COUNTRY $LANGUAGE> /dev/null &
    ret=$?
    sleep 1
    provisioner_pid=`process_status`
    
    if [ "$ret" -ne 0 -o $provisioner_pid -eq 0 ]
    then
       errlog "Provisioner couldn't start correctly"
       exit 1
    else
       ps -Fp $provisioner_pid
    fi

fi

exit 0
