package com.maids.LMS.service.impl;

import com.maids.LMS.dto.PatronDto;
import com.maids.LMS.dto.PatronResponseDto;
import com.maids.LMS.enums.Role;
import com.maids.LMS.exception.Custom.DuplicateResourceException;
import com.maids.LMS.exception.Custom.ResourceNotFoundException;
import com.maids.LMS.model.Patron;
import com.maids.LMS.repository.PatronRepository;
import com.maids.LMS.service.PatronService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }


    @Override
    public ApiResponse getPatron(String id) {
        // find patron by id
        Patron patron = patronRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Patron",
                        "id",
                        id,
                        "Patron not found"
                )
        );
        // return the response
        return ApiResponse.builder()
                .data(
                        PatronResponseDto.builder()
                                .id(patron.getId())
                                .username(patron.getUsername())
                                .email(patron.getEmail())
                                .phoneNumber(patron.getPhoneNumber())
                                .name(patron.getName())
                                .surname(patron.getSurname())
                                .build()
                )
                .message("Patron found")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse createPatron(PatronDto patronDto) {
        // check username and email are unique
        if (patronRepository.existsByUsername(patronDto.getUsername())) {
            throw new DuplicateResourceException(
                    "Patron",
                    "username",
                    patronDto.getUsername(),
                    "Username is already taken"
            );
        }

        if (patronRepository.existsByEmail(patronDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Patron",
                    "email",
                    patronDto.getEmail(),
                    "Email is already taken"
            );
        }

        // create patron object
        Patron patron = Patron.builder()
                .username(patronDto.getUsername())
                .email(patronDto.getEmail())
                .phoneNumber(patronDto.getPhoneNumber())
                .name(patronDto.getName())
                .surname(patronDto.getSurname())
                .roles(List.of(Role.ROLE_PATRON))
                .build();
        // save patron
        patronRepository.save(patron);

        // send verifications email to patron
        // TODO: send email

        // return the response
        return ApiResponse.builder()
                .data(
                        PatronResponseDto.builder()
                                .id(patron.getId())
                                .username(patron.getUsername())
                                .email(patron.getEmail())
                                .phoneNumber(patron.getPhoneNumber())
                                .name(patron.getName())
                                .surname(patron.getSurname())
                                .build()
                )
                .message("Patron created successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse updatePatron(String id, PatronDto patronDto) {
        // find patron by id
        Patron patron = patronRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Patron",
                        "id",
                        id,
                        "Patron not found"
                )
        );

        // check if username is unique
        if (!patron.getUsername().equals(patronDto.getUsername()) && patronRepository.existsByUsername(patronDto.getUsername())) {
            throw new DuplicateResourceException(
                    "Patron",
                    "username",
                    patronDto.getUsername(),
                    "Username is already taken"
            );
        }

        // check if email is unique
        if (!patron.getEmail().equals(patronDto.getEmail()) && patronRepository.existsByEmail(patronDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Patron",
                    "email",
                    patronDto.getEmail(),
                    "Email is already taken"
            );
        }

        // update patron object
        patron.setUsername(patronDto.getUsername());
        patron.setEmail(patronDto.getEmail());
        patron.setPhoneNumber(patronDto.getPhoneNumber());
        patron.setName(patronDto.getName());
        patron.setSurname(patronDto.getSurname());

        // save patron
        patronRepository.save(patron);

        // return the response
        return ApiResponse.builder()
                .data(
                        PatronResponseDto.builder()
                                .id(patron.getId())
                                .username(patron.getUsername())
                                .email(patron.getEmail())
                                .phoneNumber(patron.getPhoneNumber())
                                .name(patron.getName())
                                .surname(patron.getSurname())
                                .build()
                )
                .message("Patron updated successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse deletePatron(String id) {
        // find patron by id
        Patron patron = patronRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Patron",
                        "id",
                        id,
                        "Patron not found"
                )
        );

        // delete patron
        patronRepository.delete(patron);

        // return the response
        return ApiResponse.builder()
                .message("Patron deleted successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse getPatrons(
            String username,
            String email,
            String phone,
            String name,
            String surname,
            int page,
            int size
    ) {
        // split parameters by comma to get lists
        List<String> usernames = username != null ? List.of(username.split(",")) : null;
        List<String> emails = email != null ? List.of(email.split(",")) : null;
        List<String> phones = phone != null ? List.of(phone.split(",")) : null;
        List<String> names = name != null ? List.of(name.split(",")) : null;
        List<String> surnames = surname != null ? List.of(surname.split(",")) : null;

        // list to store all criteria
        List<Criteria> criteriaList = new ArrayList<>();

        if (usernames != null && !usernames.isEmpty()) {
            List<Criteria> usernameCriteriaList = usernames.stream()
                    .map(usernameStr -> Criteria.where("username").is(usernameStr))
                    .toList();
            criteriaList.add(new Criteria().orOperator(usernameCriteriaList.toArray(new Criteria[0])));
        }

        if (emails != null && !emails.isEmpty()) {
            List<Criteria> emailCriteriaList = emails.stream()
                    .map(emailStr -> Criteria.where("email").is(emailStr))
                    .toList();
            criteriaList.add(new Criteria().orOperator(emailCriteriaList.toArray(new Criteria[0])));
        }

        if (phones != null && !phones.isEmpty()) {
            List<Criteria> phoneCriteriaList = phones.stream()
                    .map(phoneStr -> Criteria.where("phoneNumber").is(phoneStr))
                    .toList();
            criteriaList.add(new Criteria().orOperator(phoneCriteriaList.toArray(new Criteria[0])));
        }

        if (names != null && !names.isEmpty()) {
            List<Criteria> nameCriteriaList = names.stream()
                    .map(nameStr -> Criteria.where("name").is(nameStr))
                    .toList();
            criteriaList.add(new Criteria().orOperator(nameCriteriaList.toArray(new Criteria[0])));
        }

        if (surnames != null && !surnames.isEmpty()) {
            List<Criteria> surnameCriteriaList = surnames.stream()
                    .map(surnameStr -> Criteria.where("surname").is(surnameStr))
                    .toList();
            criteriaList.add(new Criteria().orOperator(surnameCriteriaList.toArray(new Criteria[0])));
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long totalCount = patronRepository.countPatronsByQuery(query);
        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Patron> patrons = patronRepository.findPatronsByQuery(query, pageable);
        List<PatronResponseDto> patronResponseDTOs = patrons.stream()
                .map(patron -> PatronResponseDto.builder()
                        .id(patron.getId())
                        .username(patron.getUsername())
                        .email(patron.getEmail())
                        .phoneNumber(patron.getPhoneNumber())
                        .name(patron.getName())
                        .surname(patron.getSurname())
                        .build()
                )
                .toList();


        return ApiResponse.builder()
                .message(patrons.isEmpty() ? "No patrons found" : "Patrons found")
                .data(patronResponseDTOs)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .page(page)
                .size(size)
                .total((int) totalCount)
                .totalPages((int) Math.ceil((double) totalCount / size))
                .success(!patrons.isEmpty())
                .build();

    }
}
