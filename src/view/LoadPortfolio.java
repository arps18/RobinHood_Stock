package view;

import controller.Features;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class implements the feature of loading portfolio in the application. It extends the
 * AbstractScreen class.It takes the name of the portfolio and date as input from the user and
 * implements them according to it.
 */
class LoadPortfolio extends AbstractScreen {

  private JLabel selectedFile;

  private JButton select;

  private JButton load;

  /**
   * This constructor provides the functionality of loading the portfolio in the application. It
   * takes various inputs from the user and according to the input provided it implements them.
   */
  LoadPortfolio() {
    super("Load Portfolio", 400, 150);

    addComponent(createLabel("Selected File: "));
    
    selectedFile = createLabel("");
    addComponent(selectedFile);

    load = new JButton("load");
    addButton(load);

    select = new JButton("select");
    addButton(select);

    addSelectListener();
  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    this.load.addActionListener(e -> f.loadPortfolio(this.selectedFile.getText()));
  }

  private void addSelectListener() {
    select.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
      fileChooser.setFileFilter(new FileNameExtensionFilter("json file", "json"));
      int option = fileChooser.showOpenDialog(null);
      if (option == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String pathStr = file.getAbsolutePath();
        selectedFile.setText(pathStr);
      }
    });
  }
}
