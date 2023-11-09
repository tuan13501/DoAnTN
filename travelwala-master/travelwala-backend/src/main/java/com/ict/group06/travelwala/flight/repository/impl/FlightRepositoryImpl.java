package com.ict.group06.travelwala.flight.repository.impl;

import com.ict.group06.travelwala.common.enumeration.seatclass.SeatClassEnum;
import com.ict.group06.travelwala.flight.entity.Flight;
import com.ict.group06.travelwala.flight.model.request.FlightCriteria;
import com.ict.group06.travelwala.flight.repository.FlightRepository;
import com.ict.group06.travelwala.common.repository.WalaRepositoryImpl;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FlightRepositoryImpl extends WalaRepositoryImpl<Flight, String> implements FlightRepository {
    @Autowired
    public FlightRepositoryImpl(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Flight.class);
    }

    @Override
    public List<Flight> findWithCriteria(FlightCriteria flightCriteria) {
        // lay ra tong so ghe ngoi (hay so ve)
        int totalPassengers = flightCriteria.getAdultCount() + flightCriteria.getChildCount() + flightCriteria.getInfantCount();
        // day la bien de luu tru Document dang Json (cai ma de truy van tim ra cac chuyen bay du so ghe)
        StringBuilder availableTicketQuery = null;
        // match Stage MongoDB: tim ra cac ban ghi co cung thanh pho muon den va muon di
        // match Stage MongoDB: tim ra cac ban ghi trong ngay(tu 00h00 den 23h59) ma khach muon di
        MatchOperation matchDepartureOperation =
                Aggregation.match(Criteria.where("departure_time").gte(flightCriteria.getDepartureDate()).lt(flightCriteria.getDepartureDate().plusDays(1))
                        .and("arrival_airports.city").is(flightCriteria.getArrivalCity()).and("departure_airports.city").is(flightCriteria.getDepartureCity()));
        // match Stage MongoDB: tim ra cac ban ghi co total la true
        MatchOperation matchTicketOperation = Aggregation.match(Criteria.where("total").is(true));

        // addFields Stage MongoDB: them 1 truong "total" vao tat ca cac ban ghi ("total" la ket qua boolean cua viec co du ve hay khong?)
        AddFieldsOperation addFieldsOperation;
        if(flightCriteria.getSeatClass().equals(SeatClassEnum.ECONOMY)) {
            availableTicketQuery = new StringBuilder().append("{ $gte: [{\n" +
                    "    $subtract: [\"$plane.maximum_economic_capacity\", \"$occupied_economic_seats\"], \n" +
                    "  } , ").append(totalPassengers).append("]}");
        } else if(flightCriteria.getSeatClass().equals(SeatClassEnum.BUSINESS)) {
            availableTicketQuery = new StringBuilder().append("{ $gte: [{\n" +
                    "    $subtract: [\"$plane.maximum_business_capacity\", \"$occupied_business_seats\"], \n" +
                    "  } , ").append(totalPassengers).append("]}");
        }
        // check null truoc khi operating
        assert availableTicketQuery != null;
        addFieldsOperation = Aggregation.addFields().addFieldWithValueOf("total", Document.parse(availableTicketQuery.toString())).build();
        Aggregation myAggFrom = Aggregation.newAggregation(matchDepartureOperation, addFieldsOperation, matchTicketOperation);
        AggregationResults<Flight> outputFrom = mongoTemplate.aggregate(myAggFrom, "flights", Flight.class);
        // tra ve 1 list cac chuyen bay trong ngay di thoa man het cac dieu kien (nhung chung ta can xu ly ngay ve)
        List <Flight> result = new ArrayList<Flight> (outputFrom.getMappedResults());
        // xu ly ngay ve (khac null tuc la chon chuyen bay khu hoi co ngay ve tra ra tu frontend)
        if(flightCriteria.getReturnDate() != null) {
            //match Stage MongoDB: tìm ra tất cả chuyến bay có thời gian khởi ngày nằm trong ngày về (tu 00h00 den 23h59)
            // match Stage MongoDB: tim ra cac ban ghi co cung thanh pho muon di va muon ve (no se hoi nguoc lai 1 chut)
            MatchOperation matchReturnOperation =
                    Aggregation.match(Criteria.where("departure_time").gte(flightCriteria.getReturnDate()).lt(flightCriteria.getReturnDate().plusDays(1))
                            .and("arrival_airports.city").is(flightCriteria.getDepartureCity()).and("departure_airports.city").is(flightCriteria.getArrivalCity()));
            Aggregation myAggTo = Aggregation.newAggregation(matchReturnOperation, addFieldsOperation, matchTicketOperation);
            AggregationResults<Flight> outputTo = mongoTemplate.aggregate(myAggTo, "flights", Flight.class);
            // đến đây chúng ta đã có được kết quả của các chuyến bay về nhưng cần phải check điều kiện thêm
            if (outputTo.getMappedResults().size() != 0){
                // vào được đây tức chúng ta đã tìm được ít nhất 1 chuyến bay về
                List<Flight> test = outputTo.getMappedResults();
                // thêm các chuyến bay về vào list các chuyến bay đi trước đó
                result.addAll(outputTo.getMappedResults());
            }
        }
        // cùng trả về kết quả nhé :))) cảm ơn đã đọc đến đây @QT99K64
        return result;
    }
}
