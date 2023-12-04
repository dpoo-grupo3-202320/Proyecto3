package interfaz.componentes;

import java.awt.TextField;

public class Texto extends TextField {
  public Texto(String texto, boolean editable) {
    setText(texto);
    setEditable(editable);
    setFocusable(editable);
    setName(texto);
  }

}
