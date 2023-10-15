package org.litesoft.validation;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.Significant;
import org.litesoft.pragmatics.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ValidatableDTOTest {
    @Test
    void testValidate() {
        OurDTO dto1 = new OurDTO( null, "XY" );
        assertEquals( "OurDTO{isTest=null, sexChromosome='XY'}"
                , dto1.toString() );

        OurDTO dto2 = new OurDTO( false, " XY" );
        assertEquals( "OurDTO{isTest=false, sexChromosome=' XY'}"
                , dto2.toString() );

        assertNotEquals( dto1, dto2 );

        dto1.validate( new Context( "dto1" ) );
        assertEquals( "OurDTO{isTest=false, sexChromosome='XY'}"
                , dto1.toString() );

        dto2.validate( new Context( "dto2" ) );
        assertEquals( "OurDTO{isTest=false, sexChromosome='XY'}"
                , dto2.toString() );

        assertEquals( dto1, dto2 );
    }

    static class OurDTO extends ValidatableDTO {
        private Boolean isTest;
        private String sexChromosome = " XY";

        public OurDTO( Boolean isTest, String sexChromosome ) {
            this.isTest = isTest;
            this.sexChromosome = sexChromosome;
        }

        @Override
        public boolean equals( Object obj ) {
            return (this == obj) || // Left to Right!
                   ((obj instanceof OurDTO)
                    && equalsNotSameNotNull( (OurDTO)obj ));
        }

        @Override
        public String toString() {
            return noMoreFields( addFields() );
        }

        @Override
        protected void levelValidate( @NotNull Context context ) {
            super.levelValidate( context );
            isTest = NotNull.ConstrainTo.valueOr( isTest, false );
            sexChromosome = Significant.AssertState.contextValue( context.withChild( "sexChromosome" ), sexChromosome );
        }

        protected boolean equalsNotSameNotNull( OurDTO them ) {
            return Objects.equals( this.isTest, them.isTest ) &&
                   Objects.equals( this.sexChromosome, them.sexChromosome ) &&
                   super.equalsNotSameNotNull( them );
        }

        @Override
        protected StringBuilder addFields() {
            StringBuilder sb = super.addFields();
            appendField( sb, "isTest", isTest );
            appendField( sb, "sexChromosome", sexChromosome );
            return sb;
        }
    }
}