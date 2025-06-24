package view;

import controller.Features;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This class implements the feature saving portfolio in the application. It extends the
 * AbstractScreen class. It takes the name of the portfolio and date as input from the user and
 * implements them according to it.
 */
class SavePortfolio extends AbstractScreen {

  private JTextField portfolioName;

  private JTextField fileName;
  private JLabel path;

  private JButton select;
  private JButton save;

  /**
   * This constructor provides the functionality of saving the respective portfolio in the
   * application. It takes inputs from the user and according to it implements them.
   */
  SavePortfolio() {
    super("Save Portfolio", 400, 220);

    portfolioName = createTextField();
    addInput("Portfolio Name: ", portfolioName);

    fileName = createTextField();
    addInputWithUnit("File Name: ", fileName, ".json");

    addComponent(createLabel("Selected Folder: "));

    path = createLabel(new File(System.getProperty("user.dir")).getPath());
    addComponent(path);

    save = new JButton("save");
    addButton(save);

    select = new JButton("select folder");
    addButton(select);

    addSelectListener();
  }

  private void addSelectListener() {
    select.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int option = fileChooser.showOpenDialog(null);
      if (option == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String pathStr = file.getAbsolutePath();
        path.setText(pathStr);
      }
    });
  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    this.save.addActionListener(
        e -> f.savePortfolio(portfolioName.getText(), path.getText() + "/" + fileName.getText()));
  }
}
