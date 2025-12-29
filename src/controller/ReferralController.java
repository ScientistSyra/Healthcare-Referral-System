package controller;

import java.util.List;
import model.Referral;
import model.ReferralManager;
import model.ReferralRepository;

public class ReferralController {

    private ReferralRepository referralRepository;
    private ReferralManager referralManager;

    public ReferralController(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
        this.referralManager = ReferralManager.getInstance();
    }

    // LOAD FROM CSV (Repository)
    public void loadReferrals() {
        referralRepository.loadReferrals();
    }

    // DISPLAY IN GUI (Repository)
    public List<Referral> getAllReferrals() {
        return referralRepository.getAllReferrals();
    }

    // CREATE NEW REFERRAL (Singleton + Repository)
    public void createReferral(Referral referral) {
        referralManager.addReferral(referral);   // Singleton usage ✔
        referralRepository.addReferral(referral);
    }

    public void deleteReferral(Referral referral) {
        referralRepository.deleteReferral(referral);
    }
}
