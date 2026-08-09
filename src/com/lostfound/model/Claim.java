package com.lostfound.model;

public class Claim {

    private int claimId;
    private int foundId;
    private int userId;
    private String claimDescription;
    private String status;

    public Claim() {
    }

    public Claim(
            int claimId,
            int foundId,
            int userId,
            String claimDescription,
            String status) {

        this.claimId = claimId;
        this.foundId = foundId;
        this.userId = userId;
        this.claimDescription = claimDescription;
        this.status = status;
    }

    // ==========================================
    // CLAIM ID
    // ==========================================

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    // ==========================================
    // FOUND ID
    // ==========================================

    public int getFoundId() {
        return foundId;
    }

    public void setFoundId(int foundId) {
        this.foundId = foundId;
    }

    // ==========================================
    // USER ID
    // ==========================================

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // ==========================================
    // CLAIM DESCRIPTION
    // ==========================================

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(
            String claimDescription) {

        this.claimDescription = claimDescription;
    }

    // ==========================================
    // STATUS
    // ==========================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}