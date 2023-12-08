package interfaz.componentes;

import javax.swing.JComboBox;

public class TCombo extends JComboBox<String> {

  public TCombo(String[] opciones, boolean editable) {
    super(opciones);
    setEditable(editable);
  }

  @Override
  public String getSelectedItem() {
    return (String) super.getSelectedItem();
  }

}
