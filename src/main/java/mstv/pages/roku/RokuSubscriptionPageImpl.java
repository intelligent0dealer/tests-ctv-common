package mstv.pages.roku;

import exception.WebDriverException;
import models.Selector;
import mstv.pages.base.ISubscriptionPage;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;
import static protocol.Button.*;

public class RokuSubscriptionPageImpl implements ISubscriptionPage {

    private final IPlatformProtocol<Selector> protocol;

    public RokuSubscriptionPageImpl(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }

    @Override
    public void chooseFreePlan() {
        protocol.pressButton(REWIND);
        if (SubscriptionPlan.FREE == getFocusedPlanIndex()) {
            protocol.pressButton(OK);
        } else {
            throw new WebDriverException("Free plan can't be chosen");
        }
    }

    @Override
    public void chooseMonthlyPlan() {
        SubscriptionPlan plan = getFocusedPlanIndex();
        if (SubscriptionPlan.FREE == plan) {
            protocol.pressButton(RIGHT);
        } else if (SubscriptionPlan.ANNUAL == plan) {
            protocol.pressButton(LEFT);
        }
        protocol.pressButton(OK);
    }

    @Override
    public void chooseAnnualPlan() {
        protocol.pressButton(FAST_FORWARD, OK);
    }

    private SubscriptionPlan getFocusedPlanIndex() {
        Selector planSelector = new Selector.Builder()
                .addElementData(tag("MarkupGrid"))
                .addParentData(attr("name", "plan_list"))
                .build();
        PlatformElement plans = protocol.findElement(planSelector);
        int itemCount = plans.getItemCount();
        int index = plans.getFocusedItemIndex();
        int diff = SubscriptionPlan.values().length - itemCount;
        return SubscriptionPlan.values()[index + diff];
    }

    private enum SubscriptionPlan {
        FREE,
        MONTHLY,
        ANNUAL
    }
}