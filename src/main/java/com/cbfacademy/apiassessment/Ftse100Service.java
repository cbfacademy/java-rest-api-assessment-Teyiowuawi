package com.cbfacademy.apiassessment;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class Ftse100Service implements Ftse100BasicCrud {
    // need to move reading of Java file elsewhere
        // Gson gson = new Gson();

    // try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
    //     companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
    // } catch (IOException e){
    //     e.printStackTrace();
    // }

    private List<Ftse100> companies = new ArrayList<>();

    // then the basic methods can be implemented here 
    // need to create a rest template also within my test file 
    // when writing to json file - additional things must be written into it!
    // could potentially add the commpanies list as a constructor within the service for use by the methods 

     @Override
    public ResponseEntity<Ftse100> addFtse100Company(Ftse100 newCompany) {
        Gson gson = new Gson();
			
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
			companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
			for (Ftse100 existingCompany : companies){
				if (newCompany.getTickerSymbol().toLowerCase().equals(existingCompany.getTickerSymbol().toLowerCase())){
					return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
                    // make sure this is a correct response
                    // attempt this to see if this ticker symbol has been reported or not
				}
				}} catch (IOException e){
					e.printStackTrace();
				}
				companies.add(newCompany);
				return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
        
    }

    @Override
    public ResponseEntity<List<Ftse100>> getAllFtse100Companies() {
                Gson gson = new Gson();
        
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
            companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
        } catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(companies);
        // define formatting to make it more visually appealing
    }

    @Override
    public ResponseEntity<Ftse100> getFtse100CompanyByTickerSymbol(String tickerSymbol){
        Gson gson = new Gson();
			
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
            companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
            for (Ftse100 company : companies){
                if (company.getTickerSymbol().toLowerCase().equals(tickerSymbol.toLowerCase())){
                    return ResponseEntity.ok(company);
                }}} catch (IOException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<Ftse100> updateFtse100Company(String tickerSymbol, Ftse100 updatedCompany) {
        
        Gson gson = new Gson();
			
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
            companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
            for (Ftse100 company : companies){
                if (company.getTickerSymbol().toLowerCase().equals(tickerSymbol.toLowerCase())){
                    int indexOfCompanyInFtse100List = companies.indexOf(company);
                    companies.set(indexOfCompanyInFtse100List, updatedCompany);
                    return ResponseEntity.ok(updatedCompany);
                }}} catch (IOException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<List<Ftse100>> deleteFtse100Company(String tickerSymbol) {	
		Gson gson = new Gson();
			
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("ftse100.json"))){
			companies = gson.fromJson(reader, new TypeToken<List<Ftse100>>() {}.getType());
			for (Ftse100 company : companies){
                    if (company.getTickerSymbol().toLowerCase().equals(tickerSymbol.toLowerCase())){
					companies.remove(company);
					return ResponseEntity.ok(companies);
				}}} catch (IOException e)
					{e.printStackTrace();
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);			
    }
    // come back to add a proper body



}
		
			//remember to find appropriate responses for HTTP requests and also appropriate exception handling
        

      
