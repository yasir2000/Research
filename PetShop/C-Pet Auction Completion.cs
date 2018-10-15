//------------------------
// C-Pet Auction Completion
// NeptuneScript 2.2
// 2012-jan-08
//------------------------

//------------------------
process petAuctionCompletion
//------------------------
{
  tasks {
    validateAccount;
    if ( ! State . orderID . valid )
    end;
    orderBilling;
    orderShipping( State . orderID );
    checkDelivery;
    if ( State . orderID . isUKBased )
    orderProcess( State . orderID );
  }
}

//------------------------
task checkDelivery
//------------------------
{
  requirements {
    needAddress : require orderID . isUKBased ;
  }
}

//------------------------
task orderBilling
//------------------------
{
  requirements {
    needAddress : require orderID . BillingAddress ;
    needPostcode : require orderID . BillingPostcode ;
  }
}

//------------------------
task orderProcess with orderID
//------------------------
{
  requirements {
    verifyComplete : require orderID . processComplete ;
  }
}

//------------------------
task orderShipping with orderID
//------------------------
{
  requirements {
    needAddress : require orderID . ShippingAddress ;
    needPostcode : require orderID . ShippingPostcode ;
    needBand : require orderID . ShippingBand ;
    //need to store this data
    saved : require orderID.ShippingSavedToDB ;
  }
}

//------------------------
task validateAccount
//------------------------
{
  requirements {
    isValid : require orderID . valid ;
  }
}

//------------------------
define nbloCheckDelivery as NBLO
//------------------------
{
  purpose {
    feature isUKBased to orderID ;
  }
  actuation
  {!-
    (print-transcript "CheckDelivery")
    (set-state-2 "orderID" "isUKBased" true)
  -!}
}

//------------------------
define nbloOrderBilling as NBLO
//------------------------
{
  purpose {
    feature BillingAddress to orderID ;
    feature BillingPostcode to orderID ;
  }
  actuation
  {!-
    (print-transcript "OrderBilling")
  -!}
}

//------------------------
define nbloOrderProcess as NBLO with id
//------------------------
{
  purpose {
    feature processComplete to orderID ;
  }
  actuation
  {!-
    (print-transcript (format "OrderProcess - [%s]" (pr-str id)) )
    (print-transcript "------------------------")
    (print-transcript "$- script C ends")
    (print-transcript "")
  -!}
}

//------------------------
define nbloOrderShipping as NBLO with id
//------------------------
{
  purpose {
    feature ShippingAddress to orderID ;
    feature ShippingPostcode to orderID ;
    feature ShippingBand to orderID ;
    feature ShippingSavedToDB to orderID ;
  }
  actuation
  {!-
    (print-transcript (format "OrderShipping - [%s]" (pr-str id)) )
  -!}
}

//------------------------
define nbloValidateAccount as NBLO
//------------------------
{
  purpose {
    feature valid to orderID ;
  }
  actuation
  {!-
    (print-transcript "$- script C starts")
    (print-transcript "------------------------")
    (print-transcript "Validating account")
    (set-state-2 "orderID" "valid" true)
  -!}
}

//------------------------
define pre-orderShipping as PAA
//------------------------
{
    provisioning {
    }
    assurance {
        //orderID.ShippingAddress is VALID;
        //orderID.ShippingDate is VALID;
    }
    accounting {
    }
}

//------------------------
define pre-orderShipping as CA-SPA
//------------------------
{
    checkUK {
        prediction {
        }
        action {
        }
    }
}
