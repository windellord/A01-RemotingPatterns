package evs2009.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import evs2009.mapping.CheckData.CheckDataInternal;
import evs2009.mapping.CheckData.CheckDataName;

public class MappingTest {
	private final JAXBContext context;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshall;

	public MappingTest() throws JAXBException {
		context = JAXBContext.newInstance(Epp.class, Command.class,
				Login.class, Response.class, Response.class, TrId.class);
		marshaller = context.createMarshaller();
		unmarshall = context.createUnmarshaller();
	}

	private Epp marshalUnmarshal(Epp epp) {
		try {
			StringWriter writer = new StringWriter();
			marshaller.marshal(epp, writer);
			System.out.println(writer.toString());
			return (Epp) unmarshall.unmarshal(new StringReader(writer
					.toString()));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testLogin() {
		String clID = "user";
		String pw = "password";
		Epp epp = new Epp(new Command(new Login(clID, pw)));
		Epp epp2 = marshalUnmarshal(epp);
		assertEquals(epp2.getCommand().getLogin().getClID(), clID);
		assertEquals(epp2.getCommand().getLogin().getPw(), pw);
	}

	@Test
	public void testLoginResponse() {
		Epp epp = new Epp(new Response(new Result("1000", "completed"),
				new TrId("ABC", "123")));
		marshalUnmarshal(epp);
		// TODO no testcase, just printing
	}

	@Test
	public void testLogout() {
		Epp epp = new Epp(new Command().setLogout().setClTRID("someId"));
		Epp epp2 = marshalUnmarshal(epp);
		assertTrue(epp2.getCommand().isLogout());
	}

	@Test
	public void testCheckReturn() {
		CheckDataInternal internal = new CheckDataInternal(new CheckDataName(
				true, "example1"), null);
		Epp epp = new Epp(new Response(new Result("1000", "Message"),
				new ResData(new CheckData(internal)), new TrId("ABC-123",
						"123-ABC")));
		marshalUnmarshal(epp);
	}

	@Test
	public void testCreate() {
		Epp epp = new Epp(new Command(new Create(new ObjectData("someName",
				new byte[] { 1, 2, 3, 7 })), "ABC-12345"));
		marshalUnmarshal(epp);
	}

	@Test
	public void testCreateResponse() {
		Epp epp = new Epp(new Response(new Result("1000", "Some Msg"),
				new ResData(new ObjectData("someName", new byte[] { 1, 2, 3, 4,
						5 })), new TrId("12345", "54321")));
		marshalUnmarshal(epp);
	}

	@Test
	public void testUpdate() {
		Epp epp = new Epp(new Command(new Update(new ObjectData("updateName",
				new byte[] { 1, 2, 3, 4, 5 })), "12345"));
		marshalUnmarshal(epp);
	}

	@Test
	public void testDelete() {
		Epp epp = new Epp(new Command(new Delete(new ObjectData("updateName",
				new byte[] { 1, 2, 3, 4, 5 })), "12345"));
		marshalUnmarshal(epp);
	}

	@Test
	public void testInfo() {
		Epp epp = new Epp(new Command(new Info(new ObjectData("infoName",
				new byte[] { 1, 2, 3, 4, 5 })), "someId"));
		Epp marshalUnmarshal = marshalUnmarshal(epp);
		epp.getCommand().getInfo().getInfo().getOnlyMetadata();
	}

	@Test
	public void testInfoResponse() {
		Epp epp = new Epp(
				new Response(new Result("1000", "Some Message"),
						new ResData(new ObjectData("someName", new byte[] { 1,
								2, 3, 4, 5 }), "12345-roid"), new TrId(
								"ABC-12345", "12345-CAB")));
		marshalUnmarshal(epp);
	}

	@Test
	public void testTransferResponse() {
		Epp epp = new Epp(new Response(new Result("1000", "MEssage"),
				new ResData(new TrnData("name", "status", "reId", "acId")),
				new TrId("cl", "svtr")));
		marshalUnmarshal(epp);
	}
}
