/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.loginn;

/**
 *
 * @author TungK
 */
public class LoginCreateError {

    protected String usernameLengthError;
    protected String passwordLengthError;
    protected String fullNameLengthError;
    protected String confirmNotMatched;
    protected String usernameIsExisted;
    protected String wrongUserNameOrPassword;

    public LoginCreateError() {
    }

    public LoginCreateError(String usernameLengthError, String passwordLengthError, String fullNameLengthError, String confirmNotMatched, String usernameIsExisted, String wrongUserNameOrPassword) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.fullNameLengthError = fullNameLengthError;
        this.confirmNotMatched = confirmNotMatched;
        this.usernameIsExisted = usernameIsExisted;
        this.wrongUserNameOrPassword = wrongUserNameOrPassword;
    }

    public String getWrongUserNameOrPassword() {
        return wrongUserNameOrPassword;
    }

    public void setWrongUserNameOrPassword(String wrongUserNameOrPassword) {
        this.wrongUserNameOrPassword = wrongUserNameOrPassword;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

}
