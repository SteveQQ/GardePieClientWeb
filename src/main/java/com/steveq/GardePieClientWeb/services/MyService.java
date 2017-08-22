package com.steveq.GardePieClientWeb.services;

import com.steveq.GardePieClientWeb.communication.models.FromClientRequest;
import com.steveq.GardePieClientWeb.communication.models.ToClientResponse;

/**
 * Created by Adam on 2017-08-20.
 */
public interface MyService {
    ToClientResponse processRequest(FromClientRequest request);
}
