//------------------------
// D-Pet Auction Analysis
// NeptuneScript 2.2
// 2012-jan-08
//------------------------

//------------------------
process petAuctionAnalysis
//------------------------
{
  tasks {
    processAuctionData;
  }
}

//------------------------
task processAuctionData
//------------------------
{
  requirements {
    clear : require auctionStatus . processed;
  }
}

//------------------------
// This NBLO meets the requirements
// of the "processAuctionData" task
// above.
//------------------------

//------------------------
define nbloProcessData as NBLO
//------------------------
{
  purpose {
    feature processed to auctionStatus ;
  }
  actuation
  {!-
    (print-transcript "$- script D starts")
    (print-transcript "------------------------")
    (let*
       [
       empty-fn
       (fn [h]
         (or
           (empty? h)
           (and
             (<= (count h) 2)
             (= :final-bid (first (keys (last h)))))))
       name-fn
       (fn [d h]
         (if (empty? d)
           "Initial"
           (cond
             (empty-fn (rest (rest h)))
             "Final"
             true
             (format "Bid/Offer %s" (count d)))))
       ]
      (loop
        [
         data []
         history @(get-state-2 "auctionRecord" "history")
         ]
        (let*
          [
           name (name-fn data history)
           offer (first (vals (first history)))
           bid (first (vals (second history)))
           diff (- offer bid)
           data2 (conj data {:name name, :offer offer, :bid bid :diff diff})
           history2 (rest (rest history))
           ]
          (if (empty-fn history2)
            (send-cmd
              {
               :cmd :showChart,
               :data data2
               })
            (recur data2 history2)))))
    (print-transcript (pr-str @(get-state-2 "auctionRecord" "history")))
    (print-transcript "------------------------")
    (print-transcript "$- script D ends")
    (print-transcript "")
  -!}
}
