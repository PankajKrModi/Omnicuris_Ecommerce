package com.omnicuris.ecommerce.service;

import java.util.regex.Pattern;

public class ValidationUtil {

  private static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$";

  private static String noRegex = "\\d{10}";

  public static boolean validateEmail(String email) {
    return Pattern.compile(emailRegex).matcher(email).matches();
  }

  public static boolean validateContact(String no) {
    return Pattern.compile(noRegex).matcher(no).matches();
  }
  
}
