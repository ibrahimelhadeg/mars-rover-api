package com.mars.rover.api;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractConstants.*;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideMetaInformationTest {

    @Test
    void should_provide_meta_information() {
        assertThat(getGeneratedApiContract().getInfo()).isNotNull();
    }

    @Test
    void should_provide_title() {
        assertThat(getGeneratedApiContract().getInfo().getTitle())
                .isEqualTo(API_NAME);
    }

    @Test
    void should_provide_description() {
        assertThat(getGeneratedApiContract().getInfo().getDescription())
                .isEqualTo(API_DESCRIPTION);
    }

    @Test
    void should_provide_contact_information() {
        var contact = getGeneratedApiContract().getInfo().getContact();
        assertThat(contact).isNotNull();

        assertThat(contact.getEmail()).isEqualTo(API_CONTACT);
    }

    @Test
    void should_provide_licence() {
        var license = getGeneratedApiContract().getInfo().getLicense();
        assertThat(license).isNotNull();

        assertThat(license.getName())
                .isEqualTo(API_LICENCE_NAME);
        assertThat(license.getUrl())
                .isEqualTo(API_LICENCE_URL);
    }

    @Test
    void should_use_semantic_versioning() {
        var semanticVersionPattern =
                Pattern.compile("[1-9]\\d*\\.\\d+\\.\\d+(?:-[a-zA-Z\\d]+)?");
        var semanticVersionMatcher =
                semanticVersionPattern.matcher(getGeneratedApiContract().getInfo().getVersion());

        assertThat(semanticVersionMatcher.matches()).isTrue();
    }

    @Test
    void should_expose_api_components() {
        assertThat(getGeneratedApiContract().getComponents()).isNotNull();
    }

    @Test
    void should_expose_api_schemas() {
        assertThat(getGeneratedApiContract()
                .getComponents()
                .getSchemas()).isNotNull();
    }
}
