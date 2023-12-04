package interfaz.componentes;

import javax.swing.JComboBox;

public class Combo extends JComboBox<String> {

  public Combo(String[] opciones, boolean editable) {
    super(opciones);
    setEditable(editable);
  }

  @Override
  public String getSelectedItem() {
    return (String) super.getSelectedItem();
  }

}
