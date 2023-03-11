package com.rosvitiazev.railways.domain.dao.interfaces;

import com.rosvitiazev.railways.domain.entities.ConfirmationToken;

public interface ConfirmationTokenDAO extends GenDAO<ConfirmationToken>{

    public ConfirmationToken getUser(int user_id);

}
