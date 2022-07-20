package com.samgines.filebytes.components;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by samgi on 7/5/2022.
 */
@Component
@Slf4j
public class FileBytesTool implements CommandLineRunner {

  @Override
  public void run(String... args) {
    main(args);
  }
  public static void main(String[] args) {
    String bytesAsString = fileToBytes();
    copyContentsToClipboard(bytesAsString);
  }

  private static String fileToBytes(){
    JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue = jFileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jFileChooser.getSelectedFile();
      log.info(selectedFile.getAbsolutePath());
      try{
        byte[] bytes = Files.readAllBytes(selectedFile.toPath());
        return Arrays.toString(bytes);
      } catch (Exception e){
        log.error(e.getMessage(), e);
      }
    }
    return "Unable to retrieve bytes.";
  }

  private static void copyContentsToClipboard(String contents){
    if(StringUtils.isNotEmpty(contents)){
      StringSelection stringSelection = new StringSelection(contents);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);
      log.info("Successfully copied contents to clipboard");
    } else {
      log.info("Contents are empty.");
    }
  }
}
