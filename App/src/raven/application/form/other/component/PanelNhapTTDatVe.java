package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.NhapTTDatVeForm;

public class PanelNhapTTDatVe extends JPanel {
    public PanelNhapTTDatVe(int maxe) {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
        NhapTTDatVeForm form = new NhapTTDatVeForm(0);
        add(form, "grow");
    }
}