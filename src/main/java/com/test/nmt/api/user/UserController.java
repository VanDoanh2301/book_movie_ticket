package com.test.nmt.api.user;

import com.test.nmt.model.ticket.Ticket;
import com.test.nmt.model.ticket.TicketFakeDto;
import com.test.nmt.model.user.User;
import com.test.nmt.model.user.UserDto2;
import com.test.nmt.repository.TicketFakeRepository;
import com.test.nmt.repository.TicketRepository;
import com.test.nmt.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.nmt.model.user.UserDTO;
import com.test.nmt.service.user.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TicketFakeRepository ticketRepo;

    @GetMapping("{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.getByID(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        User patient = userRepo.findByEmailAndPassword(email, password);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> newPatient(@RequestBody UserDto2 userDto2) throws ParseException {
        if (userRepo.existsByEmail(userDto2.getEmail())) {
            return ResponseEntity.ok("Email is variable");
        }
        User patient = new User();
        BeanUtils.copyProperties(userDto2, patient);
        userRepo.save(patient);
        return ResponseEntity.ok("successfully");
    }

    @PostMapping("newTicketTime")
    public ResponseEntity<?> newTicket(@RequestBody TicketFakeDto ticketFakeDto)  {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketFakeDto, ticket);
        ticketRepo.save(ticket);
        return ResponseEntity.ok("successfully");
    }

    @GetMapping("getTicket/{name}")
    public ResponseEntity<?> getTicket(@PathVariable(value = "name") String name)  {
        List<Ticket> tickets = ticketRepo.findByUserName(name);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("getTicket")
    public ResponseEntity<?> getAllTicket()  {
        List<Ticket> tickets = ticketRepo.findAll();
        return ResponseEntity.ok(tickets);
    }
    @GetMapping("deleteTicket/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable(value = "id") Long id)  {
        ticketRepo.deleteByTicketID(id);
        return ResponseEntity.ok("Delete");
    }


}