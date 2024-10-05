package Projet_Calbo.utilis;

import java.util.regex.Pattern;

import Projet_Calbo.model.Role;

public class InputValidateur {
	
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean validateTeamName(String teamName) {
        if (teamName == null || teamName.trim().isEmpty()) {
            return false;
        }
        return teamName.length() >= 3;  
    }

    public static boolean validateId(String id) {
        try {
            int parsedId = Integer.parseInt(id);
            return parsedId > 0;  
        } catch (NumberFormatException e) {
            return false;  
        }
    }
    
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }



    public static boolean isValidEquipeId(String equipeId) {
        try {
            return equipeId != null && Integer.parseInt(equipeId) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
