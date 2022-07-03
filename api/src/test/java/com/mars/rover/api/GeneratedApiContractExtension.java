package com.mars.rover.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

/**
 * This extension will retrieve and parse the generated API Contract by the SwaggerMavenPlugin
 * That's allowing to get a JSON/YAML files of generated based on all the annotations and declaration on API module
 */
public class GeneratedApiContractExtension
        implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static final String API_CONTRACT_PATH = "target/mars-rover-api.json";
    private static boolean initialized = false;

    private static OpenAPI GENERATED_API_CONTRACT;

    public static OpenAPI getGeneratedApiContract() {
        return GENERATED_API_CONTRACT;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!initialized) {
            initialized = true;

            // The following line registers a callback hook when the root test context is shut down
            context.getRoot().getStore(GLOBAL).put(this.getClass().getCanonicalName(), this);
            var parseOptions = new ParseOptions();
            parseOptions.setResolve(false);
            parseOptions.setResolveFully(false);

            GENERATED_API_CONTRACT = new OpenAPIV3Parser().read(API_CONTRACT_PATH, null, parseOptions);
        }
    }

    @Override
    public void close() {
        if (!initialized) {
            throw new RuntimeException("The Extension was never triggered!");
        }

        GENERATED_API_CONTRACT = null;
    }
}