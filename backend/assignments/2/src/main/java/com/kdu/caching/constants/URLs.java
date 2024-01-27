package com.kdu.caching.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing URLs used in geocoding/reverse geocoding
 */
@AllArgsConstructor
@Getter
public enum URLs {
    GEOCODING_URL("http://api.positionstack.com/v1/forward"),
    REVERSE_GEOCODING_URL("http://api.positionstack.com/v1/reverse"),
    API_KEY("4e57b023d214af3066ba77225070d93b");

    final String value;
}
