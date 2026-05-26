package com.bajaj.api.service;

import com.bajaj.api.dto.BfhlRequest;
import com.bajaj.api.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processData(BfhlRequest request);
}
