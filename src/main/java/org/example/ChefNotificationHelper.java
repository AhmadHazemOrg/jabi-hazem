package org.example;

public class ChefNotificationHelper {
    private boolean substitutionRequested = false;
    private boolean substitutionConfirmed = false;
    private boolean chefAlertReceived = false;
    private boolean chefApproved = false;
    private boolean chefModified = false;

    private String originalIngredient;
    private String substitutedIngredient;
    private String chefAlternativeIngredient;

    public void requestSubstitution(String original, String substitute) {
        this.substitutionRequested = true;
        this.originalIngredient = original;
        this.substitutedIngredient = substitute;
    }

    public void confirmSubstitution() {
        if (substitutionRequested) {
            this.substitutionConfirmed = true;
            this.chefAlertReceived = true;
        }
    }

    public boolean isChefAlertReceived() {
        return chefAlertReceived;
    }

    public String getOriginalIngredient() {
        return originalIngredient;
    }

    public String getSubstitutedIngredient() {
        return substitutedIngredient;
    }

    public void approveSubstitution() {
        this.chefApproved = true;
    }

    public boolean isChefApproved() {
        return chefApproved;
    }

    public void modifySubstitution(String newAlternative) {
        this.chefModified = true;
        this.chefAlternativeIngredient = newAlternative;
    }

    public boolean isChefModified() {
        return chefModified;
    }

    public String getChefAlternativeIngredient() {
        return chefAlternativeIngredient;
    }
}
