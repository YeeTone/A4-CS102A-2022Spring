import org.junit.Test;

import java.math.BigInteger;
import java.security.Permission;

import static org.junit.Assert.assertTrue;

public class TestBigBinaryOfficial {

    static {
        SecurityManager sm = new SecurityManager() {
            @Override
            public void checkPackageAccess(String pkg) {
                super.checkPackageAccess(pkg);
                if (pkg.equals("java.math")) {
                    throw new RuntimeException("You cannot use java.math.BigInteger or java.math.BigDecimal in this problem!");
                }
            }

            @Override
            public void checkPermission(Permission perm) {
                if(perm.getName().equals("setSecurityManager")){
                    throw new RuntimeException("You cannot reset SecurityManager in this problem!");
                }
            }
        };

        System.setSecurityManager(sm);

    }


    @Test
    public void test1() {
        assertTrue(123L == usingBigInteger());
    }

    public long usingBigInteger(){
        BigInteger bi = new BigInteger("123");
        return bi.longValueExact();
    }
}
