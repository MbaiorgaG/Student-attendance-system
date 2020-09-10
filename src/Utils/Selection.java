package Utils;

import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;

import java.util.ArrayList;
import java.util.List;

public class Selection {
	private final ReaderCollection m_collection;
	private List<Reader> list_reader;

	public Selection(ReaderCollection m_collection) {
		this.m_collection = m_collection;
		list_reader = new ArrayList<>();
	}

	public void refresh_list()  {
		try {
			m_collection.GetReaders();
		} catch (UareUException exception) {
			exception.printStackTrace();
		}

		for(Reader item:m_collection){
			list_reader.add(item);
		}

	}


	public String selectReader(){
		String readerName = "";
		if (list_reader.size() == -1){
			return null;
		}
		for (int i =0;i<list_reader.size(); i++){
			 readerName = list_reader.get(i).GetDescription().name;
		}
		return readerName;
	}

	public Reader getSelectedReader(String selectedReader) {
		int index = 0;
		for(Reader p : list_reader) {
			if (p.GetDescription().name.equals(selectedReader)){
				index = list_reader.indexOf(p);
			}
		}
		return m_collection.get(index);
	}


	public static void main(String[] arg) throws UareUException {
		 ReaderCollection m_collection;
		 Reader reader;

		m_collection = UareUGlobal.GetReaderCollection();
		Selection selection = new Selection(m_collection);
		selection.refresh_list();// refreshes the collection of readers
		String selectedReader = selection.selectReader();
		reader = selection.getSelectedReader(selectedReader);
		System.out.println("index of the reader selected:" + reader);

	}


}
