package com.rosvitiazev.railways.domain.dao.interfaces;


import com.rosvitiazev.railways.domain.entities.Ticket;
import com.rosvitiazev.railways.domain.entities.UserTicket;

import java.util.List;

public interface UserTicketDAO extends GenDAO<UserTicket> {

    public List<Ticket> getTicketByUserId(int user_id);

}
