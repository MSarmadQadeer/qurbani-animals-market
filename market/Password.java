package market;

import java.io.Serializable;


public class Password implements Serializable {
    private String password;
    private String favouriteWord;

    public Password() {
        this.setPassword();
    }

    public void setFavouriteWord(String favouriteWord) {
        this.favouriteWord = favouriteWord;
    }

    public String getFavouriteWord() {
        return favouriteWord;
    }

    private void setPassword() {
        for (int i = 0; true; i++) {
            String pass1 = Input.inputLine("Enter Password : ");
            String pass2 = Input.inputLine("Confirm Password : ");
            if (pass1.equals(pass2)) {
                this.password = pass1;
                System.out.println("\n(It Will Help You In Changing Your Password)");
                setFavouriteWord(Input.inputWord("Enter Your Favourite Word : "));
                break;
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER IN CONSCIOUSNESS");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword() {
        for (int i = 0; true; i++) {
            String fav = Input.inputLine("\nEnter Your Favourite Word : ");
            if (fav.equals(getFavouriteWord())) {
                this.setPassword();
                break;
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER IN CONSCIOUSNESS");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }
}
