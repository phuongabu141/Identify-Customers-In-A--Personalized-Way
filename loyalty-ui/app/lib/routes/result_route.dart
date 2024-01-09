import 'package:app/views/result/result_all.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:app/views/label/all.dart';

enum ResultNestedRoute {
  view,
  detail;

  String get route {
    return '/$name';
  }

  Widget get widget {
    switch (this) {
      case ResultNestedRoute.view:
        return ResultView();
      case ResultNestedRoute.detail:
        return ResultDetail();
    }
  }

  static GetPageRoute getPage(RouteSettings settings) {
    var route = ResultNestedRoute.values
        .firstWhereOrNull((e) => e.route == settings.name);
    return GetPageRoute(page: () => route?.widget ?? Container());
  }
}
