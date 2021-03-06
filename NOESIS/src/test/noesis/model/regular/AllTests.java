package test.noesis.model.regular;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { test.noesis.model.regular.CompleteNetworkTest.class,
					   test.noesis.model.regular.IsolateNetworkTest.class,
					   test.noesis.model.regular.RingNetworkTest.class,
					   test.noesis.model.regular.TandemNetworkTest.class,
					   test.noesis.model.regular.StarNetworkTest.class,
					   test.noesis.model.regular.BinaryTreeNetworkTest.class,
					   test.noesis.model.regular.MeshNetworkTest.class,
					   test.noesis.model.regular.ToroidalNetworkTest.class,
					   test.noesis.model.regular.HypercubeNetworkTest.class
					 } )
public class AllTests {

}
