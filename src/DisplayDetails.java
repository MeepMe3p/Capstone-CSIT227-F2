import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DisplayDetails {
    int location;
    String selected;
    JTextArea enemiesInfo;
    JTextArea jobsInfo;

    public DisplayDetails(int location, String selected, JTextArea enemiesInfo, JTextArea jobsInfo) {
        this.location = location;
        this.selected = selected;
        this.enemiesInfo = enemiesInfo;
        this.jobsInfo = jobsInfo;
    }
    public void displayDetails(){
        if(location == 1){
            displayJobs();
        }
        if(location ==2){
            displayEnemies();
        }
    }
    private void displayJobs(){
        jobsInfo.setText("");

        String symbol;

            String charDetails = "src/CharacterDetails";

            try {
                String details = Files.readString(Paths.get(charDetails));

                symbol = switch (selected) {
                    case "Priest" -> "!";
                    case "Knight" -> "@";
                    case "Mage" -> "#";
                    default -> "";
                };

                int startSymbol = details.indexOf(symbol);
                int endSymbol = details.indexOf(symbol, startSymbol + 1);

                if (startSymbol != -1 && endSymbol != -1) {
                    jobsInfo.setText(details.substring(startSymbol + symbol.length(), endSymbol));
                } else {
                    jobsInfo.setText("Not found!");
                }

            } catch (IOException io) {
                throw new RuntimeException(io);
            }

    }
    private void displayEnemies() {
        enemiesInfo.setText("");
        String symbol;
        String charDetails = "src/CharacterDetails";
        try {
            String details = Files.readString(Paths.get(charDetails));
            symbol = switch (selected) {
                case "Scorpion" -> "$";
                case "Suicide Rock" -> "%";
                case "Skeleton" -> "^";
                case "Dark Stalker" -> "&";
                case "Ancient Bishop" -> "*";
                default -> "";
            };
            int startSymbol = details.indexOf(symbol);
            int endSymbol = details.indexOf(symbol, startSymbol + 1);
            if (startSymbol != -1 && endSymbol != -1) {
                enemiesInfo.setText(details.substring(startSymbol + symbol.length(), endSymbol));
            } else {
                enemiesInfo.setText("Not found!");
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
    }
}
