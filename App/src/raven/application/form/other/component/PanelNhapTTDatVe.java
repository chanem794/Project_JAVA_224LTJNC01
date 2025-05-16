package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.NhapTTDatVeForm;
import raven.application.form.other.StationForm;

public class PanelNhapTTDatVe extends JPanel {
    public PanelNhapTTDatVe(int maXe, StationForm previousForm) {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
        NhapTTDatVeForm form = new NhapTTDatVeForm(maXe, previousForm); // Truyền cả maXe và previousForm
        add(form, "grow");
    }
}