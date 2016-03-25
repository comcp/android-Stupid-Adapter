package com.stupid.method.demo.activity;

import org.xml.sax.SAXException;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidquery.util.XmlDom;
import com.stupid.method.adapter.XRecylerAdapter;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.IqiyiRoot;
import com.stupid.method.demo.bean.Vlist;
import com.stupid.method.demo.holder.VlistViewHolder;
import com.stupid.method.demo.util.JsonUtils;

public class RecylerActivity extends BaseActivity {

	private RecyclerView recyclerView;
	private XRecylerAdapter<Vlist> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyler);
		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		// 创建一个线性布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		// 设置布局管理器
		recyclerView.setLayoutManager(layoutManager);
		adapter = new XRecylerAdapter<Vlist>(VlistViewHolder.class, null);
		recyclerView.setAdapter(adapter);
		recyclerView.setOnScrollListener(adapter.getOnScrollListener(null));
		getHttp().get(50, "http://cache.video.iqiyi.com/jp/avlist/202861101/",
				this);

		try {
			String str = "<RequestData><HeadData><UserCode>sh1_admin</UserCode><UserName>sh1_admin</UserName><UserCompanyCode>3107</UserCompanyCode><UserCompanyName>上海分公司一部</UserCompanyName><RequestType>03</RequestType></HeadData><BodyData><ReportId>113100000033</ReportId><Insurant>a5rfg87</Insurant><NumberPlate>沪E78612</NumberPlate><EngineModel></EngineModel><CarVin></CarVin><AccidentDate>2011-02-25 15:07:00</AccidentDate><ReportDate>2011-02-25 15:07:00</ReportDate><Province>310000</Province><City>310100</City><District></District><AccidentPlace>1</AccidentPlace><AccidentLongitude></AccidentLongitude><AccidentLatitude></AccidentLatitude><SurveyLongitude></SurveyLongitude><SurveyLatitude></SurveyLatitude><SceneReportFlag></SceneReportFlag><Reporter></Reporter><ReporterTel></ReporterTel><SurveyPlace></SurveyPlace><OperatorId>3525</OperatorId><OperatorName>sh_admin</OperatorName><ReportDealId>30000800</ReportDealId><ReportDealName>江苏分公司</ReportDealName><CompanyName></CompanyName><CustomerTypeCode></CustomerTypeCode><ForcePolicyId>a5rfg87a5rfg87a5rfg87</ForcePolicyId><BizPolicyId></BizPolicyId><Index>0</Index><FieldName>5</FieldName></BodyData></RequestData>";

			XmlDom dom = new XmlDom(str);
			dom.tag("UserCode").text();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onServerResult(int resultCode, String data, boolean state,
			int statusCode) {

		IqiyiRoot root = JsonUtils.parseObject(
				data.replace("var tvInfoJs=", ""), IqiyiRoot.class);
		if (null == root) {
			showToast("服务器异常");

		}
		adapter.addAll(root.getData().getVlist());
		adapter.notifyDataSetChanged();
	}

	{

		/**
		 * 就是这个应用程序从SDCard获取文书，
		 * 
		 * 如果获取不到就弹出对话框文件找不到，
		 * 
		 * 但是如果在6.0的设备上，
		 * 
		 * 可以将访问SDCard的权限关闭掉，
		 * 
		 * 如果关闭权限就提示没有权限
		 */

	}

}
