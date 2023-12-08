package interfaz.componentes;

import java.awt.TextField;

public class TText extends TextField {
  public TText(String texto, boolean editable) {
    setText(texto);
    setEditable(editable);
    setFocusable(editable);
    setName(texto);
  }

}
