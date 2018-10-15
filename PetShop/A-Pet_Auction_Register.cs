//------------------------
// A-Pet Auction Register
// NeptuneScript 2.2
// 2012-jan-08
//------------------------

//------------------------
// This script starts the
// Pet Auction process by
// obtaining the name and
// type of the pet for sale,
// the names of the seller
// and buyer, and the start
// time of the auction.
//------------------------


//------------------------
// There are two tasks
// defined by the top-level
// process. The first task
// clears all previous data.
// And then the seconds task
// obtains the auction details.
//------------------------

//------------------------
process petAuctionRegister
//------------------------
{
  tasks {
    clearAuctionData;
    obtainAuctionDetails;
  }
}

//------------------------
// Tasks state requirements
// which will be matched against
// the "purpose" section in each
// NBLO. Code is dynamically generated
// and compiled at runtime to invoke
// matching NBLO's.
//------------------------

//------------------------
task clearAuctionData
//------------------------
{
  requirements {
    clear : require auctionStatus . cleared;
  }
}

//------------------------
task obtainAuctionDetails
//------------------------
{
  requirements {
    requestDetailsFromUser : require auctionStatus . auctionDetails;
  }
}

//------------------------
// This NBLO meets the requirements
// of the "clearAuctionData" task
// above.
//------------------------

//------------------------
define nbloClearAuction as NBLO
//------------------------
{
  purpose {
    feature cleared to auctionStatus ;
  }
  actuation
  {!-
    ;--------
    ; Clear the internal state variables
    ; and send a command to the browser to
    ; clear the output panels.
    ;--------
    (clear-state)
    (send-cmd {:cmd "clearAll"})
  -!}
}

//------------------------
// This NBLO meets the requirements
// of the "obtainAuctionDetails" task
// above. It sends a command to the Neptune
// browser client to open the "auctionDetail"
// user form.
//------------------------

//------------------------
define nbloObtainDetails as NBLO
//------------------------
{
  purpose {
    feature auctionDetails to auctionStatus ;
  }
  actuation
  {!-
    (let*
        [
        callbackFn
        '(fn [data]
        (let*
        [
            pet-name (data :name)
            pet-type (data :type)
            seller-name (data :seller)
            buyer-name (data :buyer)
            auction-start (last (clojure.string/split (data :time) #"[T]"))
        ]
        ;--------
        ; Store the data from the user
        ;--------
        (set-state-2 :data :pet-name pet-name)
        (set-state-2 :data :pet-type pet-type)
        (set-state-2 :data :seller-name seller-name)
        (set-state-2 :data :buyer-name buyer-name)
        (set-state-2 :data :auction-start auction-start)
        ;--------
        ; Show the results in the Transcript panel
        ;--------
        (print-transcript "$- script A starts")
        (print-transcript "------------------------")
        (print-transcript "Auction Registration")
        (print-transcript "------------------------")
        (print-transcript (format "      start: %s" auction-start))
        (print-transcript (format "   pet name: %s" pet-name))
        (print-transcript (format "   pet type: %s" pet-type))
        (print-transcript (format "seller name: %s" seller-name))
        (print-transcript (format " buyer name: %s" buyer-name))
        (print-transcript "------------------------")
        (print-transcript "$- script A ends")
        (print-transcript "")))
        data {:callbackFn (pr-str callbackFn)}
        ]
        (send-cmd {
            :cmd "createWidget"
            :def "auctionDetails"
            :data (json-str data)
        }))
  -!}
}
