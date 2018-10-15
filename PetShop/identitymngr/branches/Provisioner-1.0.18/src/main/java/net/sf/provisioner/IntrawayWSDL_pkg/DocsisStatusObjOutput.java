/**
 * DocsisStatusObjOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public class DocsisStatusObjOutput  implements java.io.Serializable {
    private java.lang.String idClienteCRM;

    private java.lang.String nombre;

    private java.lang.String username;

    private java.lang.String macaddress;

    private java.lang.String productName;

    private java.lang.String manufacturer;

    private java.lang.String hwv;

    private java.lang.String swv;

    private java.lang.String ip;

    private java.lang.String cmts;

    private java.lang.String upStream;

    private java.lang.String downStream;

    private java.lang.String ispCM;

    private java.lang.String ispMTA;

    private java.lang.String ispCPE;

    private java.lang.String servicePackage;

    private java.lang.String docsisVersion;

    private java.lang.String cantPCs;

    private java.lang.String disabled;

    private java.lang.String cantMensajes;

    private java.lang.String upTraffic;

    private java.lang.String downTraffic;

    private java.lang.String hub;

    private java.lang.String nodo;

    private java.lang.String uspl;

    private java.lang.String dspl;

    private java.lang.String dssnr;

    private java.lang.String ussnr;

    private java.lang.String sysuptime;

    private java.lang.String status;

    private net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cmLeases;

    private net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cpeLeases;

    private net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] mtaLeases;

    private net.sf.provisioner.IntrawayWSDL_pkg.MensajesObj[] mensajes;

    private net.sf.provisioner.IntrawayWSDL_pkg.PoolingObj[] pooling;

    private net.sf.provisioner.IntrawayWSDL_pkg.SpDescriptionObj spDescription;

    public DocsisStatusObjOutput() {
    }

    public DocsisStatusObjOutput(
           java.lang.String idClienteCRM,
           java.lang.String nombre,
           java.lang.String username,
           java.lang.String macaddress,
           java.lang.String productName,
           java.lang.String manufacturer,
           java.lang.String hwv,
           java.lang.String swv,
           java.lang.String ip,
           java.lang.String cmts,
           java.lang.String upStream,
           java.lang.String downStream,
           java.lang.String ispCM,
           java.lang.String ispMTA,
           java.lang.String ispCPE,
           java.lang.String servicePackage,
           java.lang.String docsisVersion,
           java.lang.String cantPCs,
           java.lang.String disabled,
           java.lang.String cantMensajes,
           java.lang.String upTraffic,
           java.lang.String downTraffic,
           java.lang.String hub,
           java.lang.String nodo,
           java.lang.String uspl,
           java.lang.String dspl,
           java.lang.String dssnr,
           java.lang.String ussnr,
           java.lang.String sysuptime,
           java.lang.String status,
           net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cmLeases,
           net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cpeLeases,
           net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] mtaLeases,
           net.sf.provisioner.IntrawayWSDL_pkg.MensajesObj[] mensajes,
           net.sf.provisioner.IntrawayWSDL_pkg.PoolingObj[] pooling,
           net.sf.provisioner.IntrawayWSDL_pkg.SpDescriptionObj spDescription) {
           this.idClienteCRM = idClienteCRM;
           this.nombre = nombre;
           this.username = username;
           this.macaddress = macaddress;
           this.productName = productName;
           this.manufacturer = manufacturer;
           this.hwv = hwv;
           this.swv = swv;
           this.ip = ip;
           this.cmts = cmts;
           this.upStream = upStream;
           this.downStream = downStream;
           this.ispCM = ispCM;
           this.ispMTA = ispMTA;
           this.ispCPE = ispCPE;
           this.servicePackage = servicePackage;
           this.docsisVersion = docsisVersion;
           this.cantPCs = cantPCs;
           this.disabled = disabled;
           this.cantMensajes = cantMensajes;
           this.upTraffic = upTraffic;
           this.downTraffic = downTraffic;
           this.hub = hub;
           this.nodo = nodo;
           this.uspl = uspl;
           this.dspl = dspl;
           this.dssnr = dssnr;
           this.ussnr = ussnr;
           this.sysuptime = sysuptime;
           this.status = status;
           this.cmLeases = cmLeases;
           this.cpeLeases = cpeLeases;
           this.mtaLeases = mtaLeases;
           this.mensajes = mensajes;
           this.pooling = pooling;
           this.spDescription = spDescription;
    }


    /**
     * Gets the idClienteCRM value for this DocsisStatusObjOutput.
     * 
     * @return idClienteCRM
     */
    public java.lang.String getIdClienteCRM() {
        return idClienteCRM;
    }


    /**
     * Sets the idClienteCRM value for this DocsisStatusObjOutput.
     * 
     * @param idClienteCRM
     */
    public void setIdClienteCRM(java.lang.String idClienteCRM) {
        this.idClienteCRM = idClienteCRM;
    }


    /**
     * Gets the nombre value for this DocsisStatusObjOutput.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DocsisStatusObjOutput.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the username value for this DocsisStatusObjOutput.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this DocsisStatusObjOutput.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the macaddress value for this DocsisStatusObjOutput.
     * 
     * @return macaddress
     */
    public java.lang.String getMacaddress() {
        return macaddress;
    }


    /**
     * Sets the macaddress value for this DocsisStatusObjOutput.
     * 
     * @param macaddress
     */
    public void setMacaddress(java.lang.String macaddress) {
        this.macaddress = macaddress;
    }


    /**
     * Gets the productName value for this DocsisStatusObjOutput.
     * 
     * @return productName
     */
    public java.lang.String getProductName() {
        return productName;
    }


    /**
     * Sets the productName value for this DocsisStatusObjOutput.
     * 
     * @param productName
     */
    public void setProductName(java.lang.String productName) {
        this.productName = productName;
    }


    /**
     * Gets the manufacturer value for this DocsisStatusObjOutput.
     * 
     * @return manufacturer
     */
    public java.lang.String getManufacturer() {
        return manufacturer;
    }


    /**
     * Sets the manufacturer value for this DocsisStatusObjOutput.
     * 
     * @param manufacturer
     */
    public void setManufacturer(java.lang.String manufacturer) {
        this.manufacturer = manufacturer;
    }


    /**
     * Gets the hwv value for this DocsisStatusObjOutput.
     * 
     * @return hwv
     */
    public java.lang.String getHwv() {
        return hwv;
    }


    /**
     * Sets the hwv value for this DocsisStatusObjOutput.
     * 
     * @param hwv
     */
    public void setHwv(java.lang.String hwv) {
        this.hwv = hwv;
    }


    /**
     * Gets the swv value for this DocsisStatusObjOutput.
     * 
     * @return swv
     */
    public java.lang.String getSwv() {
        return swv;
    }


    /**
     * Sets the swv value for this DocsisStatusObjOutput.
     * 
     * @param swv
     */
    public void setSwv(java.lang.String swv) {
        this.swv = swv;
    }


    /**
     * Gets the ip value for this DocsisStatusObjOutput.
     * 
     * @return ip
     */
    public java.lang.String getIp() {
        return ip;
    }


    /**
     * Sets the ip value for this DocsisStatusObjOutput.
     * 
     * @param ip
     */
    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }


    /**
     * Gets the cmts value for this DocsisStatusObjOutput.
     * 
     * @return cmts
     */
    public java.lang.String getCmts() {
        return cmts;
    }


    /**
     * Sets the cmts value for this DocsisStatusObjOutput.
     * 
     * @param cmts
     */
    public void setCmts(java.lang.String cmts) {
        this.cmts = cmts;
    }


    /**
     * Gets the upStream value for this DocsisStatusObjOutput.
     * 
     * @return upStream
     */
    public java.lang.String getUpStream() {
        return upStream;
    }


    /**
     * Sets the upStream value for this DocsisStatusObjOutput.
     * 
     * @param upStream
     */
    public void setUpStream(java.lang.String upStream) {
        this.upStream = upStream;
    }


    /**
     * Gets the downStream value for this DocsisStatusObjOutput.
     * 
     * @return downStream
     */
    public java.lang.String getDownStream() {
        return downStream;
    }


    /**
     * Sets the downStream value for this DocsisStatusObjOutput.
     * 
     * @param downStream
     */
    public void setDownStream(java.lang.String downStream) {
        this.downStream = downStream;
    }


    /**
     * Gets the ispCM value for this DocsisStatusObjOutput.
     * 
     * @return ispCM
     */
    public java.lang.String getIspCM() {
        return ispCM;
    }


    /**
     * Sets the ispCM value for this DocsisStatusObjOutput.
     * 
     * @param ispCM
     */
    public void setIspCM(java.lang.String ispCM) {
        this.ispCM = ispCM;
    }


    /**
     * Gets the ispMTA value for this DocsisStatusObjOutput.
     * 
     * @return ispMTA
     */
    public java.lang.String getIspMTA() {
        return ispMTA;
    }


    /**
     * Sets the ispMTA value for this DocsisStatusObjOutput.
     * 
     * @param ispMTA
     */
    public void setIspMTA(java.lang.String ispMTA) {
        this.ispMTA = ispMTA;
    }


    /**
     * Gets the ispCPE value for this DocsisStatusObjOutput.
     * 
     * @return ispCPE
     */
    public java.lang.String getIspCPE() {
        return ispCPE;
    }


    /**
     * Sets the ispCPE value for this DocsisStatusObjOutput.
     * 
     * @param ispCPE
     */
    public void setIspCPE(java.lang.String ispCPE) {
        this.ispCPE = ispCPE;
    }


    /**
     * Gets the servicePackage value for this DocsisStatusObjOutput.
     * 
     * @return servicePackage
     */
    public java.lang.String getServicePackage() {
        return servicePackage;
    }


    /**
     * Sets the servicePackage value for this DocsisStatusObjOutput.
     * 
     * @param servicePackage
     */
    public void setServicePackage(java.lang.String servicePackage) {
        this.servicePackage = servicePackage;
    }


    /**
     * Gets the docsisVersion value for this DocsisStatusObjOutput.
     * 
     * @return docsisVersion
     */
    public java.lang.String getDocsisVersion() {
        return docsisVersion;
    }


    /**
     * Sets the docsisVersion value for this DocsisStatusObjOutput.
     * 
     * @param docsisVersion
     */
    public void setDocsisVersion(java.lang.String docsisVersion) {
        this.docsisVersion = docsisVersion;
    }


    /**
     * Gets the cantPCs value for this DocsisStatusObjOutput.
     * 
     * @return cantPCs
     */
    public java.lang.String getCantPCs() {
        return cantPCs;
    }


    /**
     * Sets the cantPCs value for this DocsisStatusObjOutput.
     * 
     * @param cantPCs
     */
    public void setCantPCs(java.lang.String cantPCs) {
        this.cantPCs = cantPCs;
    }


    /**
     * Gets the disabled value for this DocsisStatusObjOutput.
     * 
     * @return disabled
     */
    public java.lang.String getDisabled() {
        return disabled;
    }


    /**
     * Sets the disabled value for this DocsisStatusObjOutput.
     * 
     * @param disabled
     */
    public void setDisabled(java.lang.String disabled) {
        this.disabled = disabled;
    }


    /**
     * Gets the cantMensajes value for this DocsisStatusObjOutput.
     * 
     * @return cantMensajes
     */
    public java.lang.String getCantMensajes() {
        return cantMensajes;
    }


    /**
     * Sets the cantMensajes value for this DocsisStatusObjOutput.
     * 
     * @param cantMensajes
     */
    public void setCantMensajes(java.lang.String cantMensajes) {
        this.cantMensajes = cantMensajes;
    }


    /**
     * Gets the upTraffic value for this DocsisStatusObjOutput.
     * 
     * @return upTraffic
     */
    public java.lang.String getUpTraffic() {
        return upTraffic;
    }


    /**
     * Sets the upTraffic value for this DocsisStatusObjOutput.
     * 
     * @param upTraffic
     */
    public void setUpTraffic(java.lang.String upTraffic) {
        this.upTraffic = upTraffic;
    }


    /**
     * Gets the downTraffic value for this DocsisStatusObjOutput.
     * 
     * @return downTraffic
     */
    public java.lang.String getDownTraffic() {
        return downTraffic;
    }


    /**
     * Sets the downTraffic value for this DocsisStatusObjOutput.
     * 
     * @param downTraffic
     */
    public void setDownTraffic(java.lang.String downTraffic) {
        this.downTraffic = downTraffic;
    }


    /**
     * Gets the hub value for this DocsisStatusObjOutput.
     * 
     * @return hub
     */
    public java.lang.String getHub() {
        return hub;
    }


    /**
     * Sets the hub value for this DocsisStatusObjOutput.
     * 
     * @param hub
     */
    public void setHub(java.lang.String hub) {
        this.hub = hub;
    }


    /**
     * Gets the nodo value for this DocsisStatusObjOutput.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this DocsisStatusObjOutput.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the uspl value for this DocsisStatusObjOutput.
     * 
     * @return uspl
     */
    public java.lang.String getUspl() {
        return uspl;
    }


    /**
     * Sets the uspl value for this DocsisStatusObjOutput.
     * 
     * @param uspl
     */
    public void setUspl(java.lang.String uspl) {
        this.uspl = uspl;
    }


    /**
     * Gets the dspl value for this DocsisStatusObjOutput.
     * 
     * @return dspl
     */
    public java.lang.String getDspl() {
        return dspl;
    }


    /**
     * Sets the dspl value for this DocsisStatusObjOutput.
     * 
     * @param dspl
     */
    public void setDspl(java.lang.String dspl) {
        this.dspl = dspl;
    }


    /**
     * Gets the dssnr value for this DocsisStatusObjOutput.
     * 
     * @return dssnr
     */
    public java.lang.String getDssnr() {
        return dssnr;
    }


    /**
     * Sets the dssnr value for this DocsisStatusObjOutput.
     * 
     * @param dssnr
     */
    public void setDssnr(java.lang.String dssnr) {
        this.dssnr = dssnr;
    }


    /**
     * Gets the ussnr value for this DocsisStatusObjOutput.
     * 
     * @return ussnr
     */
    public java.lang.String getUssnr() {
        return ussnr;
    }


    /**
     * Sets the ussnr value for this DocsisStatusObjOutput.
     * 
     * @param ussnr
     */
    public void setUssnr(java.lang.String ussnr) {
        this.ussnr = ussnr;
    }


    /**
     * Gets the sysuptime value for this DocsisStatusObjOutput.
     * 
     * @return sysuptime
     */
    public java.lang.String getSysuptime() {
        return sysuptime;
    }


    /**
     * Sets the sysuptime value for this DocsisStatusObjOutput.
     * 
     * @param sysuptime
     */
    public void setSysuptime(java.lang.String sysuptime) {
        this.sysuptime = sysuptime;
    }


    /**
     * Gets the status value for this DocsisStatusObjOutput.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this DocsisStatusObjOutput.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the cmLeases value for this DocsisStatusObjOutput.
     * 
     * @return cmLeases
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] getCmLeases() {
        return cmLeases;
    }


    /**
     * Sets the cmLeases value for this DocsisStatusObjOutput.
     * 
     * @param cmLeases
     */
    public void setCmLeases(net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cmLeases) {
        this.cmLeases = cmLeases;
    }


    /**
     * Gets the cpeLeases value for this DocsisStatusObjOutput.
     * 
     * @return cpeLeases
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] getCpeLeases() {
        return cpeLeases;
    }


    /**
     * Sets the cpeLeases value for this DocsisStatusObjOutput.
     * 
     * @param cpeLeases
     */
    public void setCpeLeases(net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] cpeLeases) {
        this.cpeLeases = cpeLeases;
    }


    /**
     * Gets the mtaLeases value for this DocsisStatusObjOutput.
     * 
     * @return mtaLeases
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] getMtaLeases() {
        return mtaLeases;
    }


    /**
     * Sets the mtaLeases value for this DocsisStatusObjOutput.
     * 
     * @param mtaLeases
     */
    public void setMtaLeases(net.sf.provisioner.IntrawayWSDL_pkg.LeasesObj[] mtaLeases) {
        this.mtaLeases = mtaLeases;
    }


    /**
     * Gets the mensajes value for this DocsisStatusObjOutput.
     * 
     * @return mensajes
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.MensajesObj[] getMensajes() {
        return mensajes;
    }


    /**
     * Sets the mensajes value for this DocsisStatusObjOutput.
     * 
     * @param mensajes
     */
    public void setMensajes(net.sf.provisioner.IntrawayWSDL_pkg.MensajesObj[] mensajes) {
        this.mensajes = mensajes;
    }


    /**
     * Gets the pooling value for this DocsisStatusObjOutput.
     * 
     * @return pooling
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.PoolingObj[] getPooling() {
        return pooling;
    }


    /**
     * Sets the pooling value for this DocsisStatusObjOutput.
     * 
     * @param pooling
     */
    public void setPooling(net.sf.provisioner.IntrawayWSDL_pkg.PoolingObj[] pooling) {
        this.pooling = pooling;
    }


    /**
     * Gets the spDescription value for this DocsisStatusObjOutput.
     * 
     * @return spDescription
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.SpDescriptionObj getSpDescription() {
        return spDescription;
    }


    /**
     * Sets the spDescription value for this DocsisStatusObjOutput.
     * 
     * @param spDescription
     */
    public void setSpDescription(net.sf.provisioner.IntrawayWSDL_pkg.SpDescriptionObj spDescription) {
        this.spDescription = spDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocsisStatusObjOutput)) return false;
        DocsisStatusObjOutput other = (DocsisStatusObjOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idClienteCRM==null && other.getIdClienteCRM()==null) || 
             (this.idClienteCRM!=null &&
              this.idClienteCRM.equals(other.getIdClienteCRM()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.macaddress==null && other.getMacaddress()==null) || 
             (this.macaddress!=null &&
              this.macaddress.equals(other.getMacaddress()))) &&
            ((this.productName==null && other.getProductName()==null) || 
             (this.productName!=null &&
              this.productName.equals(other.getProductName()))) &&
            ((this.manufacturer==null && other.getManufacturer()==null) || 
             (this.manufacturer!=null &&
              this.manufacturer.equals(other.getManufacturer()))) &&
            ((this.hwv==null && other.getHwv()==null) || 
             (this.hwv!=null &&
              this.hwv.equals(other.getHwv()))) &&
            ((this.swv==null && other.getSwv()==null) || 
             (this.swv!=null &&
              this.swv.equals(other.getSwv()))) &&
            ((this.ip==null && other.getIp()==null) || 
             (this.ip!=null &&
              this.ip.equals(other.getIp()))) &&
            ((this.cmts==null && other.getCmts()==null) || 
             (this.cmts!=null &&
              this.cmts.equals(other.getCmts()))) &&
            ((this.upStream==null && other.getUpStream()==null) || 
             (this.upStream!=null &&
              this.upStream.equals(other.getUpStream()))) &&
            ((this.downStream==null && other.getDownStream()==null) || 
             (this.downStream!=null &&
              this.downStream.equals(other.getDownStream()))) &&
            ((this.ispCM==null && other.getIspCM()==null) || 
             (this.ispCM!=null &&
              this.ispCM.equals(other.getIspCM()))) &&
            ((this.ispMTA==null && other.getIspMTA()==null) || 
             (this.ispMTA!=null &&
              this.ispMTA.equals(other.getIspMTA()))) &&
            ((this.ispCPE==null && other.getIspCPE()==null) || 
             (this.ispCPE!=null &&
              this.ispCPE.equals(other.getIspCPE()))) &&
            ((this.servicePackage==null && other.getServicePackage()==null) || 
             (this.servicePackage!=null &&
              this.servicePackage.equals(other.getServicePackage()))) &&
            ((this.docsisVersion==null && other.getDocsisVersion()==null) || 
             (this.docsisVersion!=null &&
              this.docsisVersion.equals(other.getDocsisVersion()))) &&
            ((this.cantPCs==null && other.getCantPCs()==null) || 
             (this.cantPCs!=null &&
              this.cantPCs.equals(other.getCantPCs()))) &&
            ((this.disabled==null && other.getDisabled()==null) || 
             (this.disabled!=null &&
              this.disabled.equals(other.getDisabled()))) &&
            ((this.cantMensajes==null && other.getCantMensajes()==null) || 
             (this.cantMensajes!=null &&
              this.cantMensajes.equals(other.getCantMensajes()))) &&
            ((this.upTraffic==null && other.getUpTraffic()==null) || 
             (this.upTraffic!=null &&
              this.upTraffic.equals(other.getUpTraffic()))) &&
            ((this.downTraffic==null && other.getDownTraffic()==null) || 
             (this.downTraffic!=null &&
              this.downTraffic.equals(other.getDownTraffic()))) &&
            ((this.hub==null && other.getHub()==null) || 
             (this.hub!=null &&
              this.hub.equals(other.getHub()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
            ((this.uspl==null && other.getUspl()==null) || 
             (this.uspl!=null &&
              this.uspl.equals(other.getUspl()))) &&
            ((this.dspl==null && other.getDspl()==null) || 
             (this.dspl!=null &&
              this.dspl.equals(other.getDspl()))) &&
            ((this.dssnr==null && other.getDssnr()==null) || 
             (this.dssnr!=null &&
              this.dssnr.equals(other.getDssnr()))) &&
            ((this.ussnr==null && other.getUssnr()==null) || 
             (this.ussnr!=null &&
              this.ussnr.equals(other.getUssnr()))) &&
            ((this.sysuptime==null && other.getSysuptime()==null) || 
             (this.sysuptime!=null &&
              this.sysuptime.equals(other.getSysuptime()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.cmLeases==null && other.getCmLeases()==null) || 
             (this.cmLeases!=null &&
              java.util.Arrays.equals(this.cmLeases, other.getCmLeases()))) &&
            ((this.cpeLeases==null && other.getCpeLeases()==null) || 
             (this.cpeLeases!=null &&
              java.util.Arrays.equals(this.cpeLeases, other.getCpeLeases()))) &&
            ((this.mtaLeases==null && other.getMtaLeases()==null) || 
             (this.mtaLeases!=null &&
              java.util.Arrays.equals(this.mtaLeases, other.getMtaLeases()))) &&
            ((this.mensajes==null && other.getMensajes()==null) || 
             (this.mensajes!=null &&
              java.util.Arrays.equals(this.mensajes, other.getMensajes()))) &&
            ((this.pooling==null && other.getPooling()==null) || 
             (this.pooling!=null &&
              java.util.Arrays.equals(this.pooling, other.getPooling()))) &&
            ((this.spDescription==null && other.getSpDescription()==null) || 
             (this.spDescription!=null &&
              this.spDescription.equals(other.getSpDescription())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIdClienteCRM() != null) {
            _hashCode += getIdClienteCRM().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getMacaddress() != null) {
            _hashCode += getMacaddress().hashCode();
        }
        if (getProductName() != null) {
            _hashCode += getProductName().hashCode();
        }
        if (getManufacturer() != null) {
            _hashCode += getManufacturer().hashCode();
        }
        if (getHwv() != null) {
            _hashCode += getHwv().hashCode();
        }
        if (getSwv() != null) {
            _hashCode += getSwv().hashCode();
        }
        if (getIp() != null) {
            _hashCode += getIp().hashCode();
        }
        if (getCmts() != null) {
            _hashCode += getCmts().hashCode();
        }
        if (getUpStream() != null) {
            _hashCode += getUpStream().hashCode();
        }
        if (getDownStream() != null) {
            _hashCode += getDownStream().hashCode();
        }
        if (getIspCM() != null) {
            _hashCode += getIspCM().hashCode();
        }
        if (getIspMTA() != null) {
            _hashCode += getIspMTA().hashCode();
        }
        if (getIspCPE() != null) {
            _hashCode += getIspCPE().hashCode();
        }
        if (getServicePackage() != null) {
            _hashCode += getServicePackage().hashCode();
        }
        if (getDocsisVersion() != null) {
            _hashCode += getDocsisVersion().hashCode();
        }
        if (getCantPCs() != null) {
            _hashCode += getCantPCs().hashCode();
        }
        if (getDisabled() != null) {
            _hashCode += getDisabled().hashCode();
        }
        if (getCantMensajes() != null) {
            _hashCode += getCantMensajes().hashCode();
        }
        if (getUpTraffic() != null) {
            _hashCode += getUpTraffic().hashCode();
        }
        if (getDownTraffic() != null) {
            _hashCode += getDownTraffic().hashCode();
        }
        if (getHub() != null) {
            _hashCode += getHub().hashCode();
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        if (getUspl() != null) {
            _hashCode += getUspl().hashCode();
        }
        if (getDspl() != null) {
            _hashCode += getDspl().hashCode();
        }
        if (getDssnr() != null) {
            _hashCode += getDssnr().hashCode();
        }
        if (getUssnr() != null) {
            _hashCode += getUssnr().hashCode();
        }
        if (getSysuptime() != null) {
            _hashCode += getSysuptime().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getCmLeases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCmLeases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCmLeases(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCpeLeases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCpeLeases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCpeLeases(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMtaLeases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMtaLeases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMtaLeases(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMensajes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMensajes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMensajes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPooling() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPooling());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPooling(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSpDescription() != null) {
            _hashCode += getSpDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocsisStatusObjOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "DocsisStatusObjOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idClienteCRM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idClienteCRM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("macaddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "macaddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manufacturer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manufacturer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hwv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hwv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("swv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "swv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upStream");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upStream"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downStream");
        elemField.setXmlName(new javax.xml.namespace.QName("", "downStream"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ispCM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ispCM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ispMTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ispMTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ispCPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ispCPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicePackage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servicePackage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docsisVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docsisVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantPCs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantPCs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantMensajes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantMensajes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upTraffic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upTraffic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downTraffic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "downTraffic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hub");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hub"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uspl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uspl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dspl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dspl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dssnr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dssnr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussnr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussnr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysuptime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sysuptime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmLeases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmLeases"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "leasesObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpeLeases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpeLeases"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "leasesObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtaLeases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mtaLeases"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "leasesObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensajes"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "mensajesObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pooling");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pooling"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "poolingObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "spDescriptionObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
