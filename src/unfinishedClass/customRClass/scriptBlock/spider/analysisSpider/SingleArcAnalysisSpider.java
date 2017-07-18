package unfinishedClass.customRClass.scriptBlock.spider.analysisSpider;

import unfinishedClass.customRClass.RStringChecker;
import unfinishedClass.customRClass.scriptBlock.ScriptBlock;
import unfinishedClass.customRClass.scriptBlock.information.InformationType;
import unfinishedClass.customRClass.scriptBlock.spider.CountableSpider;

/**
 * 对单个弧线声明进行分析，
 * 保证只有两个Block，
 * 分别声明了弧线的两端。
 */
public class SingleArcAnalysisSpider extends CountableSpider {

	public SingleArcAnalysisSpider(ScriptBlock targetBlock) {
		super(targetBlock);
	}

	@Override
	public void countWork() {
		//保证只有两个Block，
		//第一个Block代表弧线的出度，
		//第二个Block代表弧线的入度。
		switch(count) {
		case 1:
			//弧线的出度， A -> B 中的A端。
			if (passTheCheck()){
				//设置出度标签。
				setInfo(InformationType.ARC_START);
			}
			break;
		case 2:
			//弧线的入度， A -> B 中的B端。
			if (passTheCheck()){
				//设置出度标签。
				setInfo(InformationType.ARC_END);
			}
			break;
		default:
			setInfo_VOID();
			descriptInfo("多余的弧线信息。");
		}
	}

	/**
	 * 对targetInfoString进行分析，
	 * 如果能够解释为一个弧线的端点，
	 * 则返回true；
	 * 否则就将targetInfomation标记为空白，
	 * 然后返回false。
	 * @return
	 * 		targetInfoString能够解释为弧线端点，返回true；
	 * 		否则返回true。
	 */
	private boolean passTheCheck() {
		if (RStringChecker.checkArcPoint(targetInfoString)) {
			return true;
		} else {
			setInfo_VOID();
			descriptInfo("非法弧线端口信息。");
			return false;
		}
	}

}
