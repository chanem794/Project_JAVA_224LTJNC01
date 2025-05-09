package bll;

import dal.BusTicketDAO;
import java.sql.SQLException;
import java.util.List;
import model.BusTicket;

public class BusTicketService {

    private BusTicketDAO busTicketDAO;

    public BusTicketService() {
        this.busTicketDAO = new BusTicketDAO();
    }

    // Lấy danh sách vé xe theo mã tuyến
    public List<BusTicket> getBusTicketsByMaTuyen(int maTuyen) throws SQLException {
        return busTicketDAO.getBusTicketsByMaTuyen(maTuyen);
    }

    // Lấy vé xe theo mã xe
    public BusTicket getBusTicketByMaXe(int maXe) throws SQLException {
        return busTicketDAO.getBusTicketByMaXe(maXe);
    }
}