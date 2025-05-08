package bll;

import dal.ConnectStationForm;
import model.Xe;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import raven.application.form.other.StationForm;
import static raven.application.form.other.PanelDataStation.createStationPanel;


public class StationService {
    private ConnectStationForm connectStationForm;
    private StationForm stationForm;

    public StationService(StationForm stationForm) throws SQLException {
        this.stationForm = stationForm;
        this.connectStationForm = new ConnectStationForm(stationForm);
    }

    public void searchPickupStations(String searchText) {
        try {
            List<Xe> xeList;
            if (searchText == null || searchText.trim().isEmpty() || searchText.equals(" 🔍  Tìm trong danh sách")) {
                xeList = connectStationForm.loadStationData();
            } else {
                xeList = connectStationForm.eventtextfield1(searchText);
            }

            // Xóa nội dung cũ
            stationForm.getRoundedPanel6().removeAll();
            stationForm.getRoundedPanel6().setLayout(new BoxLayout(stationForm.getRoundedPanel6(), BoxLayout.Y_AXIS));

            // Tạo panel cho mỗi xe
            for (Xe xe : xeList) {
                JPanel pickupPanel = createStationPanel(
                    xe.getGioDi().toString(),
                    xe.getDiemDi(),
                    xe.getDiemDi()
                );
                stationForm.getRoundedPanel6().add(pickupPanel);
            }

            stationForm.getRoundedPanel6().revalidate();
            stationForm.getRoundedPanel6().repaint();
            stationForm.revalidate();
            stationForm.repaint();
            stationForm.addRadioButtonListeners();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm kiếm điểm đón: " + e.getMessage());
        }
    }

    public void searchDropoffStations(String searchText) {
        try {
            List<Xe> xeList;
            if (searchText == null || searchText.trim().isEmpty() || searchText.equals(" 🔍  Tìm trong danh sách")) {
                xeList = connectStationForm.loadStationData();
            } else {
                xeList = connectStationForm.eventtextfield2(searchText);
            }

            // Xóa nội dung cũ
            stationForm.getRoundedPanel8().removeAll();
            stationForm.getRoundedPanel8().setLayout(new BoxLayout(stationForm.getRoundedPanel8(), BoxLayout.Y_AXIS));

            // Tạo panel cho mỗi xe
            for (Xe xe : xeList) {
                JPanel dropoffPanel = createStationPanel(
                    xe.getGioDen().toString(),
                    xe.getDiemDen(),
                    xe.getDiemDen()
                );
                stationForm.getRoundedPanel8().add(dropoffPanel);
            }

            // Cập nhật giao diện
            stationForm.getRoundedPanel8().revalidate();
            stationForm.getRoundedPanel8().repaint();
            stationForm.revalidate();
            stationForm.repaint();
            stationForm.addRadioButtonListeners();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm kiếm điểm trả: " + e.getMessage());
        }
    }

    public String getFare(String pickupStation, String dropoffStation) {
        try {
            return connectStationForm.getprice(pickupStation, dropoffStation);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy giá vé: " + e.getMessage());
        }
    }
}