package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Term;


@Getter
@RequiredArgsConstructor
public enum MyErrorCode implements Term{
	
	NAME_NOT_FOUND ("name not found", "ERR1"),
	ID_NOT_FOUND ("id not found", "ERR2");
	
	final String category;
	final String code;


}
