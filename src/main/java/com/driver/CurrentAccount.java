package com.driver;

import java.util.ArrayList;
import java.util.List;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance, 5000);
        if (balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if (tradeLicenseId == null || tradeLicenseId.isEmpty()) {
            throw new Exception("Valid License can not be generated");
        }

        int n = tradeLicenseId.length();
        char prev = tradeLicenseId.charAt(0);
        int[] counts = new int[26]; // array to count the frequency of each character
        counts[prev - 'A']++;

        for (int i = 1; i < n; i++) {
            char curr = tradeLicenseId.charAt(i);
            if (prev == curr) {
                throw new Exception("Valid License can not be generated");
            }
            counts[curr - 'A']++;
            prev = curr;
        }

        boolean valid = false;
        List<String> permutations = new ArrayList<>();
        generatePermutations("", tradeLicenseId, permutations);
        for (String perm : permutations) {
            int[] permCounts = new int[26];
            prev = perm.charAt(0);
            permCounts[prev - 'A']++;
            for (int i = 1; i < n; i++) {
                char curr = perm.charAt(i);
                if (prev == curr) {
                    break; // not a valid license ID
                }
                permCounts[curr - 'A']++;
                prev = curr;
            }
            boolean sameCounts = true;
            for (int i = 0; i < 26; i++) {
                if (counts[i] != permCounts[i]) {
                    sameCounts = false;
                    break;
                }
            }
            if (sameCounts) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new Exception("Valid License can not be generated");
        }
    }

    private void generatePermutations(String prefix, String suffix, List<String> result) {
        int n = suffix.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1), result);
            }
        }
    }
}
