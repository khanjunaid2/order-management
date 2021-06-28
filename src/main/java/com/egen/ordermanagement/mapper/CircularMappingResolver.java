package com.egen.ordermanagement.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Handles circular mappings while DTO to entity or entity to DTO conversion.
 */
public class CircularMappingResolver {

        private final Map<Object, Object> tracedInstances = new IdentityHashMap<>();

        @BeforeMapping
        public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
            return targetType.cast(tracedInstances.get(source));
        }

        @BeforeMapping
        public void storeMappedInstance(Object source, @MappingTarget Object target) {
            tracedInstances.put(source, target);
        }
}
