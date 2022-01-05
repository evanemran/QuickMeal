package com.evanemran.quickmeal.Listeners;

import com.evanemran.quickmeal.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> responses, String message);
    void didError(String message);
}
