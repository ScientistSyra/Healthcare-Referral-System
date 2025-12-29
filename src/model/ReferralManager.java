package model;

import java.util.ArrayList;
import java.util.List;

public class ReferralManager {

    private static ReferralManager instance;
    private List<Referral> referralQueue;

    private ReferralManager() {
        referralQueue = new ArrayList<>();
    }

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void addReferral(Referral referral) {
        referralQueue.add(referral);
    }

    public List<Referral> getReferrals() {
        return referralQueue;
    }

    public boolean referralExists(String referralId) {
        for (Referral r : referralQueue) {
            if (r.getReferralId().equals(referralId)) {
                return true;
            }
        }
        return false;
    }
}
