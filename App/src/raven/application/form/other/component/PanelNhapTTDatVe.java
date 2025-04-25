
package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.NhapTTDatVeForm;

public class PanelNhapTTDatVe extends JPanel {
    public PanelNhapTTDatVe() {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");

        // Thêm form nhập thực tế vào panel
        NhapTTDatVeForm form = new NhapTTDatVeForm();
        add(form, "grow");
    }
}
