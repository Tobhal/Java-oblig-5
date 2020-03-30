package com.company.model;

public class UserInput {
    private String userInput;

    // Declaration
    public UserInput() {       // This is here so i do not need to define a input
        //this.userInput = sc.next();      // Gets sc (scanner) from main
        isInputCommand(userInput);
    }
    public UserInput(String input) {
        this.userInput = input;
    }       // Here so i can pass thrue a string

    // Functions
    public void printInput() {
        System.out.println(userInput);
    }

    public void isInputCommand(String userInput) {
        char[] ch = userInput.toCharArray();
        if (ch[0] == '/') {     // Test if the first character in the stirng is a / if not the do nothing
            switch (userInput) {
                case "/exit":
                    //running = false;
                    break;
                case "/commands":
                case "/help":
                    //listCommands();
                    break;
                case "/clear":
                    //clearConsoll();
                    break;
                case "/give up":
                case "/help!":
                    try {
                        String url_open ="http://bit.ly/loaf_is_the_best";
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));        // Opens a new browser window to the URL that i spesefied
                    } catch(Exception ignored) {}
                    break;
                case "/loaf":
                    printLoafTurtle();
                    break;
                default:
                    break;
            }
        }
    }

    // Set
    public void setInput(String input) {
        this.userInput = input ;
    }

    // Get
    public String getString() {
        return userInput;
    }
    public int getInt() {
        return Integer.parseInt(userInput);
    }
    public float getFloat() {
        return Float.parseFloat(userInput);
    }
    public double getDouble() {
        return Double.parseDouble(userInput);
    }
    public long getLong() {
        return Long.parseLong(userInput);
    }
    public boolean getBool() {
        return Boolean.parseBoolean(userInput);
    }

    public static void printLoafTurtle() {
        String loafTurtle = "*****************************,***//(((/**,............................,,,,,,,,,\n" +
                "***************,,,,,,,,,*/(%%%%%%%%###/#%#%#%%%%#*,.....,,,,,,,,,,,,,,,,,,,,***\n" +
                "***********,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "*********,,,,,,,,,,*#%%%%%%%%%#(*#%%%#%%%%%%%###(////**,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "****************/%&%%%%%%%%%#/(%%%%%##(#%#%#((//*%%%#(((((,..................,,\n" +
                "*************/%%%%%%%%%%%%#(/#%####((######(/**##(##(//////*,...........,,,,,,,\n" +
                "************%%%#%%%%##((((//#%%%##/%##(///(/*/(/(#///((/**//**,,,,,,,,,,,,,,,,,\n" +
                "*****,,,,*####(((#%%%%%#//%%%%#%#%%###(#/**%#((((***(((/(//**,**,,,,,,,,,,,,,,,\n" +
                "******,,*##((%%%%%%###(*(%#######((((/(/*(##/(/////((*******,/(/*..............\n" +
                "********((#(#########(*%%###########((/*##/(#/(///**/((*****///**,.............\n" +
                "*******/##((#(##(#(((*####(#(((#(((((/*##((//////*(//*(***(//*****,.....,,,,,,,\n" +
                "****(#####((#(#(#(/(*(((((((((((((((//#((((*//**/(////**,/********,,,,,,,,,,,,,\n" +
                "***#%#(##((#((((((///////////*/***/**///((((/*//*////(**//////****,,,,,,,,,,,,,\n" +
                "**((#(#((((((((/*****//(((##(#(//**##(/(////***///(((((############(#%%/.......\n" +
                "*/%#((##((((((/(//*//(((/(*///(/**((/(////*//((((/(((#################(#(......\n" +
                "****(%&&%(((((((/**(/(//////*/***(/////*/////(((/(((####################(*.....\n" +
                "*****/#%%%(((/(//*/(////*/******//////////((/(//(((((##########((((((((((/,,,,,\n" +
                "*******/(#%#((((//((///////****/////*/////((///(((((##(##(((((/,*/((((((((/,,,,\n" +
                "****,,,,*##%%#(((((((((((((((/////****/*//////((((((((((((((((////((///(///,,,,\n" +
                "*****,,,,,*(#%%#((((#(((///(###((((//////(/(/(/(((((((((((((((((//////////(*,,,\n" +
                "****,,,,,,,,*/##%%(#%%%####/**((((((((/////(/((((((((((((((((/////////*/*/&*...\n" +
                "***********,,,,**/#%%##((/(((**///////////(#(/(((/////////(////((//********,...\n" +
                "**************,,,,#####(((////**////////((###/////////////////&@&(*********....\n" +
                "***********,*,,*,*####(((//**//(*,,,,***/%%&&&///////////////////*********.....\n" +
                "***************,,,(%%#((////(*.........,,(##%%%////////////////////#&&&/*......\n" +
                "*******,,*,,,,,,,,,,,,*//*,,............,*(####%%///////////////////////,,,,,,,\n" +
                "*********,,,,,,,,,,,,,,,,,.................*(###%%%//////////////#%#/,,,,,,,,\n" +
                "*********,*,,,,,,,,,,,,*,,.,.....,........,,,*/(####%%%%%&&&&&%%%%##/,,,,,,,,,,\n" +
                "*********,*,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,*/((#########(/*,,,,,,,,,,,,*\n" +
                "***********,,,,*,**,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,*,,    \n" +
                "Loaf Turtle!!!!!\n";


        System.out.println(loafTurtle);
    }
}
