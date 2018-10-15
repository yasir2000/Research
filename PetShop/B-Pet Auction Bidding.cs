//------------------------
// B-Pet Auction Bidding
// NeptuneScript 2.2
// 2012-jan-08
//------------------------

//------------------------
process petAuctionBidding
//------------------------
{
  tasks {
    openAuction;
    acceptBids;
    closeAuction;
  }
}

//------------------------
task openAuction
//------------------------
{
  requirements {
    open : require auctionStatus . isOpen;
    buyerStrategy : require fluents . buyerStrategy;
    sellerStrategy : require fluents . sellerStrategy;
    offer : require auctionRecord . offerPrice ;
    bid : require auctionRecord . bidPrice ;
  }
}

//------------------------
task acceptBids
//------------------------
{
  requirements {
    bidding : require auctionStatus . biddingCompleted;
  }
}

//------------------------
task closeAuction
//------------------------
{
  requirements {
    sale : require auctionStatus . salePrice ;
    closed : require auctionStatus . isClosed;
  }
}

//------------------------
define nbloReportSalePrice as NBLO
//------------------------
{
  purpose {
    feature salePrice to auctionStatus ;
  }
  actuation
  {!-
    (let*
    [
    bid
    (get-state-2 "auctionRecord" "bidPrice")
    offer
    (get-state-2 "auctionRecord" "offerPrice")
    ]
    (push-atom-list (get-state-2 "auctionRecord" "history") {:final-offer offer})
    (push-atom-list (get-state-2 "auctionRecord" "history") {:final-bid bid})
    (print-transcript "------------------------")
    (print-transcript (format "Final offer price -----: %s" offer))
    (print-transcript (format "Final bid price -------: %s" bid))
    (print-transcript "------------------------")
    (if (>= bid offer)
        (do
            (set-state-2 "auctionStatus" "salePrice" bid)
            (print-transcript (format "Sale price ------------: %s" (get-state-2 "auctionStatus" "salePrice"))))
        (do
            (set-state-2 "auctionStatus" "salePrice" 0)
            (print-transcript "Pet was not sold"))))
  -!}
}

//------------------------
define nbloSellerStrategy as NBLO
//------------------------
{
  purpose {
    feature sellerStrategy to fluents ;
  }
  actuation
  {!-
    (print-transcript "PDDL resolving seller strategy")
    (pddl-problem
        (define (problem pet-auction)
            (:domain petshop)
            (:init
                (= (initial-offer) 475)
                (= (offer-fn) (fn [bd off cnt] (- off (Math/max 2 (int (/ (- off bd) 5)))))))
            (:goal (max-price))
        )
    )
    (print-output (format "fluents offer-fn [%s]" (type (get-state-2 "fluents" "offer-fn"))))
    ;(set-state-2 "fluents" "offer-fn" (fn [bd off cnt] (- off 35)))
    (print-output (format "fluents offer-fn [%s]" (type (get-state-2 "fluents" "offer-fn"))))
  -!}
}

//------------------------
define nbloBuyerStrategy as NBLO
//------------------------
{
  purpose {
    feature buyerStrategy to fluents ;
  }
  actuation
  {!-
    (print-transcript "PDDL resolving buyer strategy")
    (pddl-problem
        (define (problem pet-auction)
            (:domain petshop)
            (:init
                (= (initial-bid) 145)
                (= (bid-fn) (fn [bd off cnt] (+ bd (int (/ (- off bd) 3))))))
            (:goal (min-price))
        )
    )
    (set-state-2 "fluents" "bid-fn" (fn [bd off cnt] (+ bd (int (/ (- off bd) 3)))))
  -!}
}

//------------------------
define nbloBidding as NBLO
//------------------------
{
  purpose {
    feature biddingCompleted to auctionStatus ;
  }
  actuation
  {!-
    (let*
        [
        prevbid
        (get-state-2 "auctionRecord" "bidPrice")
        prevoffer
        (get-state-2 "auctionRecord" "offerPrice")
        ]
        (loop [
            bid prevbid
            offer prevoffer
            count 0
            ]
            (if (and (< count 10) (< bid offer))
                (let*
                    [
                    bid-fn (get-state-2 "fluents" "bid-fn")
                    offer-fn (get-state-2 "fluents" "offer-fn")
                    newbid (bid-fn bid offer count)
                    newoffer (offer-fn bid offer count)
                    seller-name (get-state-2 :data :seller-name)
                    buyer-name (get-state-2 :data :buyer-name)
                    ]
                    (push-atom-list (get-state-2 "auctionRecord" "history") {:offer newoffer})
                    (push-atom-list (get-state-2 "auctionRecord" "history") {:bid newbid})
                    (print-transcript "---------")
                    (print-transcript (format "%s offers ------: %s" seller-name newoffer))
                    (print-transcript (format "%s bids --------: %s" buyer-name newbid))
                    (recur newbid newoffer (inc count)))
                (do
                    (set-state-2 "auctionRecord" "bidPrice" bid)
                    (set-state-2 "auctionRecord" "offerPrice" offer)))))
  -!}
}

//------------------------
define nbloCloseAuction as NBLO
//------------------------
{
  purpose {
    feature isClosed to auctionStatus ;
  }
  actuation
  {!-
    (set-state-2 "auctionStatus" "isClosed" true)
    (print-transcript "------------------------")
    (print-transcript "Pet Auction is Now Closed")
    (print-transcript "------------------------")
    (print-transcript "$- script B ends")
    (print-transcript "")
  -!}
}

//------------------------
define nbloOpenAuction as NBLO
//------------------------
{
  purpose {
    feature isOpen to auctionStatus ;
  }
  actuation
  {!-
    (set-state-2 "auctionStatus" "isOpen" true)
    (set-state-2 "auctionRecord" "history" (atom []))
    (print-transcript "$- script B starts")
    (print-transcript "------------------------")
    (print-transcript "Pet Auction is Now Open")
    (print-transcript "------------------------")
  -!}
}

//------------------------
define nbloGetInitialOfferPrice as NBLO
//------------------------
{
  purpose {
    feature offerPrice to auctionRecord ;
  }
  actuation
  {!-
    (set-state-2 "auctionRecord" "offerPrice" (get-state-2 "fluents" "initial-offer"))
    (push-atom-list (get-state-2 "auctionRecord" "history") {:initial-offer (get-state-2 "fluents" "initial-offer")})
    (print-transcript "---------")
    (print-transcript (format "Initial offer price ---: %s" (get-state-2 "auctionRecord" "offerPrice")))
  -!}
}

//------------------------
define nbloGetInitialBidPrice as NBLO
//------------------------
{
  purpose {
    feature bidPrice to auctionRecord ;
  }
  actuation
  {!-
    (set-state-2 "auctionRecord" "bidPrice" (get-state-2 "fluents" "initial-bid"))
    (push-atom-list (get-state-2 "auctionRecord" "history") {:initial-bid (get-state-2 "fluents" "initial-bid")})
    (print-transcript (format "Initial bid price -----: %s" (get-state-2 "auctionRecord" "bidPrice")))
  -!}
}
