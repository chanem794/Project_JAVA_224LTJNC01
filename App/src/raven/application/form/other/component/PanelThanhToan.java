package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.ThanhToanForm;
import raven.application.form.other.StationForm;

public class PanelThanhToan extends JPanel {
    public PanelThanhToan(int maXe, String tenNguoiDi, String sdt, String email, int totalCost, boolean hasInsurance, StationForm previousForm) {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
        ThanhToanForm form = new ThanhToanForm(maXe, tenNguoiDi, sdt, email, totalCost, hasInsurance, previousForm); // Truyền thêm totalCost và hasInsurance
        add(form, "grow");
    }
}