package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Term;
import tw.com.fcb.mimosa.domain.t9n.Translated;
import tw.com.fcb.mimosa.domain.t9n.TranslationService;

@Service
public class MyErrorTranslation implements TranslationService{

	@Override
	public Optional<Translated> translate(@NonNull Term term) {
		// TODO Auto-generated method stub
		
		//作法一
		//因為DB可能沒有建對應表 所以回傳的物件設定為Optional
		if(term.getCode().equals("ERR1")) {
			return Optional.of(new Translated() {

				@Override
				public @NotBlank String getCategory() {
					// TODO Auto-generated method stub
					return term.getCategory();
				}

				@Override
				public @NotBlank String getCode() {
					// TODO Auto-generated method stub
					return term.getCode();
				}

				@Override
				public String getTranslation() {
					// TODO Auto-generated method stub
					return "查無姓名";
				}
				
			});
		}
		//return Optional.empty();
		
		//作法二
		return Optional.of(new MyTranslation(term.getCategory(), term.getCode(), "查無姓名"));
	}
	
	
	@Getter
	//宣告此命名代表建構子要帶所有的欄位, 才能夠初始化
	@RequiredArgsConstructor
	static class MyTranslation implements Translated{
		final String category;
		final String code;
		final String translation;
	}

}
