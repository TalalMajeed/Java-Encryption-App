package com.talalmajeed.encryptionapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;
public class MainController {


    @FXML
    Button encrypt;

    @FXML
    Button decrypt;
    @FXML
    TextArea input;

    @FXML
    TextArea output;

    @FXML
    Button operate;

    @FXML
    TextField key;

    protected int mode;
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    public void initialize(){
        encrypt.setStyle("-fx-background-color: #b177fc");
        decrypt.setStyle("-fx-background-color: #7530cf");
        mode = 0;
    }

    @FXML
    protected void shiftMode(ActionEvent event) {
        //Get the ID Of the button
        Button clickedButton = (Button) event.getSource();
        String buttonID = clickedButton.getId();

        if(buttonID.equals("encrypt")){
            encrypt.setStyle("-fx-background-color: #b177fc");
            decrypt.setStyle("-fx-background-color: #7530cf");
            input.setPromptText("Enter the Text to Encrypt in this box");
            output.setPromptText("The Decrypted Data will appear here");
            operate.setText("Encrypt Data");
            key.setPromptText("Leave Blank for Random");
            input.setText("");
            output.setText("");
            mode = 0;
        }
        else if(buttonID.equals("decrypt")){
            decrypt.setStyle("-fx-background-color: #b177fc");
            encrypt.setStyle("-fx-background-color: #7530cf");
            input.setPromptText("Enter the Text to Decrypt in this box");
            output.setPromptText("The Decrypted Data will appear here");
            operate.setText("Decrypt Data");
            key.setPromptText("Enter the Key for Decryption");
            input.setText("");
            output.setText("");
            mode = 1;
        }
    }

    public static String encrypt(String plainText, String key) throws Exception {
        if(key.length() != 32 && key.length() != 16){
            return "Invalid Key Length: " + key.length() + " characters. Key must be 16 or 32 characters long.";
        }
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] cipherText = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        }
        catch (Exception e){
            return "Unable to Decrypt Data. Please check the Key and try again.";
        }

    }
    @FXML void operateClick() throws Exception {
        String inputText = input.getText();
        String keyText = key.getText();
        String outputText = "";
        if(mode == 0) {
            if(keyText.isEmpty()) {

                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                int length = 16;

                Random random = new Random();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; i++) {
                    int randomIndex = random.nextInt(characters.length());
                    sb.append(characters.charAt(randomIndex));
                }
                keyText = sb.toString();
                key.setText(keyText);
            }
            output.setText(encrypt(inputText, keyText));
        }
        else if(mode == 1) {
            output.setText(decrypt(inputText, keyText));
        }
    }

}