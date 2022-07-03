package com.mars.rover.api;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractConstants.TAG_COMMANDER;
import static com.mars.rover.api.ApiContractConstants.TAG_COMMANDER_DESCRIPTION;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideTagsTest {

    @Test
    void should_provide_api_tags() {
        assertThat(getGeneratedApiContract().getTags()).isNotNull();

        assertThat(getGeneratedApiContract().getTags()).hasSize(1);
    }

    @Test
    void should_provide_commander_tags() {
        should_provide_tag(TAG_COMMANDER, TAG_COMMANDER_DESCRIPTION);
    }

    private void should_provide_tag(final String tagName,
                                    final String tagDescription) {
        var maybeCommanderTag =
                getGeneratedApiContract().getTags()
                        .stream()
                        .filter(tagItem -> tagName.equals(tagItem.getName()))
                        .findFirst();
        assertThat(maybeCommanderTag).isPresent();

        var commanderTag = maybeCommanderTag.get();
        assertThat(commanderTag.getDescription()).isEqualTo(tagDescription);
    }

    @Test
    void should_tag_get_rover_state_endpoint_with_commander_tag() {
        var getRoverStateOperationTags =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getGet()
                        .getTags();
        check_endpoint_tags(getRoverStateOperationTags);
    }

    @Test
    void should_tag_post_rover_state_endpoint_with_commander_tag() {
        var postRoverStateOperationTags =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getPost()
                        .getTags();
        check_endpoint_tags(postRoverStateOperationTags);
    }

    private void check_endpoint_tags(final List<String> tags) {
        assertThat(tags).isNotNull().hasSize(1);

        assertThat(tags.get(0)).isEqualTo(TAG_COMMANDER);
    }
}
